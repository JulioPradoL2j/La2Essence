package org.l2jmobius.gameserver.network.clientpackets;

import java.util.Arrays;

import org.l2jmobius.gameserver.ai.Intention;
import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.data.xml.DoorData;
import org.l2jmobius.gameserver.geoengine.GeoEngine;
import org.l2jmobius.gameserver.managers.ZoneBuildManager;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.SayuneEntry;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.AdminTeleportType;
import org.l2jmobius.gameserver.model.events.EventDispatcher;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.actor.player.OnPlayerMoveRequest;
import org.l2jmobius.gameserver.model.events.returns.TerminateReturn;
import org.l2jmobius.gameserver.model.skill.enums.FlyType;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.enums.SayuneType;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import org.l2jmobius.gameserver.network.serverpackets.FlyToLocation;
import org.l2jmobius.gameserver.network.serverpackets.MagicSkillLaunched;
import org.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;
import org.l2jmobius.gameserver.network.serverpackets.sayune.ExFlyMove;
import org.l2jmobius.gameserver.network.serverpackets.sayune.ExFlyMoveBroadcast;
import org.l2jmobius.gameserver.util.Broadcast;

public class MoveToLocation extends ClientPacket
{
	private int _targetX;
	private int _targetY;
	private int _targetZ;
	private int _originX;
	private int _originY;
	private int _originZ;
	private int _movementMode;

	@Override
	protected void readImpl()
	{
		this._targetX = this.readInt();
		this._targetY = this.readInt();
		this._targetZ = this.readInt();
		this._originX = this.readInt();
		this._originY = this.readInt();
		this._originZ = this.readInt();
		this._movementMode = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (player.isOverloaded())
			{
				player.sendPacket(SystemMessageId.YOU_CANNOT_MOVE_DUE_TO_THE_WEIGHT_OF_YOUR_INVENTORY);
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else if (PlayerConfig.PLAYER_MOVEMENT_BLOCK_TIME > 0 && !player.isGM() && player.getNotMoveUntil() > System.currentTimeMillis())
			{
				player.sendPacket(SystemMessageId.YOU_CANNOT_MOVE_WHILE_SPEAKING_TO_AN_NPC_ONE_MOMENT_PLEASE);
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else if (this._targetX == this._originX && this._targetY == this._originY && this._targetZ == this._originZ)
			{
				player.stopMove(player.getLocation());
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else if (GeoEngine.getInstance().isCompletelyBlocked(GeoEngine.getGeoX(this._targetX), GeoEngine.getGeoY(this._targetY), this._targetZ))
			{
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else if (DoorData.getInstance().checkIfDoorsBetween(player.getLastServerPosition(), player.getLocation(), player.getInstanceWorld()))
			{
				player.stopMove(player.getLastServerPosition());
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else
			{
				if (this._movementMode == 1)
				{
					player.setCursorKeyMovement(false);
					if (EventDispatcher.getInstance().hasListener(EventType.ON_PLAYER_MOVE_REQUEST, player))
					{
						TerminateReturn terminate = EventDispatcher.getInstance().notifyEvent(new OnPlayerMoveRequest(player, new Location(this._targetX, this._targetY, this._targetZ)), player, TerminateReturn.class);
						if (terminate != null && terminate.terminate())
						{
							player.sendPacket(ActionFailed.STATIC_PACKET);
							return;
						}
					}
				}
				else
				{
					if (!PlayerConfig.ENABLE_KEYBOARD_MOVEMENT)
					{
						return;
					}

					player.setCursorKeyMovement(true);
					player.setLastServerPosition(player.getX(), player.getY(), player.getZ());
				}

				AdminTeleportType teleMode = player.getTeleMode();
				switch (teleMode)
				{
					case DEMONIC:
						player.sendPacket(ActionFailed.STATIC_PACKET);
						player.teleToLocation(new Location(this._targetX, this._targetY, this._targetZ));
						player.setTeleMode(AdminTeleportType.NORMAL);
						break;
					case SAYUNE:
						player.sendPacket(new ExFlyMove(player, SayuneType.ONE_WAY_LOC, -1, Arrays.asList(new SayuneEntry(false, -1, this._targetX, this._targetY, this._targetZ))));
						player.setXYZ(this._targetX, this._targetY, this._targetZ);
						Broadcast.toKnownPlayers(player, new ExFlyMoveBroadcast(player, SayuneType.ONE_WAY_LOC, -1, new Location(this._targetX, this._targetY, this._targetZ)));
						player.setTeleMode(AdminTeleportType.NORMAL);
						break;
					case CHARGE:
						player.setXYZ(this._targetX, this._targetY, this._targetZ);
						Broadcast.toSelfAndKnownPlayers(player, new MagicSkillUse(player, 30012, 10, 500, 0));
						Broadcast.toSelfAndKnownPlayers(player, new FlyToLocation(player, this._targetX, this._targetY, this._targetZ, FlyType.CHARGE));
						Broadcast.toSelfAndKnownPlayers(player, new MagicSkillLaunched(player, 30012, 10));
						player.sendPacket(ActionFailed.STATIC_PACKET);
						break;
					case ZONE_POINT:
						ZoneBuildManager.getInstance().addPoint(player, new Location(this._targetX, this._targetY, this._targetZ));
						player.sendPacket(ActionFailed.STATIC_PACKET);
						return;
					default:
						if (player.isControlBlocked())
						{
							player.sendPacket(ActionFailed.STATIC_PACKET);
							return;
						}

						double dx = this._targetX - player.getX();
						double dy = this._targetY - player.getY();
						if (dx * dx + dy * dy > 9.801E7)
						{
							player.sendPacket(ActionFailed.STATIC_PACKET);
							return;
						}

						player.getAI().setIntention(Intention.MOVE_TO, new Location(this._targetX, this._targetY, this._targetZ));
				}

				if (player.getQueuedSkill() != null)
				{
					player.setQueuedSkill(null, null, false, false);
				}

				player.onActionRequest();
			}
		}
	}
}
