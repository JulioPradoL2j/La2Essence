package org.l2jmobius.gameserver.network.clientpackets.teleports;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.data.holders.TeleportListHolder;
import org.l2jmobius.gameserver.data.xml.NpcData;
import org.l2jmobius.gameserver.data.xml.RaidTeleportListData;
import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.managers.DatabaseSpawnManager;
import org.l2jmobius.gameserver.managers.GrandBossManager;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.npc.RaidBossStatus;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;
import org.l2jmobius.gameserver.model.effects.EffectFlag;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.teleports.ExRaidTeleportInfo;

public class ExTeleportToRaidPosition extends ClientPacket
{
	private int _raidId;

	@Override
	protected void readImpl()
	{
		this._raidId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			TeleportListHolder teleport = RaidTeleportListData.getInstance().getTeleport(this._raidId);
			if (teleport == null)
			{
				PacketLogger.warning("No registered teleport location for raid id: " + this._raidId);
			}
			else if (player.isDead())
			{
				player.sendPacket(SystemMessageId.DEAD_CHARACTERS_CANNOT_USE_TELEPORTATION);
			}
			else
			{
				NpcTemplate template = NpcData.getInstance().getTemplate(this._raidId);
				if (template.isType("GrandBoss") && GrandBossManager.getInstance().getStatus(this._raidId) != 0)
				{
					player.sendPacket(SystemMessageId.YOU_CANNOT_TELEPORT_RIGHT_NOW);
				}
				else if (template.isType("RaidBoss") && DatabaseSpawnManager.getInstance().getStatus(this._raidId) != RaidBossStatus.ALIVE)
				{
					player.sendPacket(SystemMessageId.YOU_CANNOT_TELEPORT_RIGHT_NOW);
				}
				else if (!player.isCastingNow() && !player.isInCombat() && !player.isImmobilized() && !player.isInInstance() && !player.isOnEvent() && !player.isInOlympiadMode() && !player.inObserverMode() && !player.isInTraingCamp() && !player.isInTimedHuntingZone())
				{
					if ((!PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_TELEPORT || !PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_USE_GK) && player.getReputation() < 0)
					{
						player.sendPacket(SystemMessageId.YOU_CANNOT_TELEPORT_RIGHT_NOW);
					}
					else if (player.isAffected(EffectFlag.CANNOT_ESCAPE))
					{
						player.sendPacket(SystemMessageId.YOU_CANNOT_TELEPORT_RIGHT_NOW);
					}
					else
					{
						Location location = teleport.getLocation();
						if (!PlayerConfig.TELEPORT_WHILE_SIEGE_IN_PROGRESS)
						{
							Castle castle = CastleManager.getInstance().getCastle(location.getX(), location.getY(), location.getZ());
							if (castle != null && castle.getSiege().isInProgress())
							{
								player.sendPacket(SystemMessageId.YOU_CANNOT_TELEPORT_TO_A_VILLAGE_THAT_IS_IN_A_SIEGE);
								return;
							}
						}

						int price;
						if (System.currentTimeMillis() - player.getVariables().getLong("LastFreeRaidTeleportTime", 0L) > 86400000L)
						{
							player.getVariables().set("LastFreeRaidTeleportTime", System.currentTimeMillis());
							price = 0;
						}
						else
						{
							price = teleport.getPrice();
						}

						if (price > 0)
						{
							if (player.getInventory().getInventoryItemCount(91663, -1) < price)
							{
								player.sendPacket(SystemMessageId.THERE_ARE_NOT_ENOUGH_L_COINS);
								return;
							}

							player.destroyItemByItemId(ItemProcessType.FEE, 91663, price, player, true);
						}

						player.abortCast();
						player.stopMove(null);
						player.setTeleportLocation(location);
						player.castTeleportSkill();
						player.sendPacket(new ExRaidTeleportInfo(player));
					}
				}
				else
				{
					player.sendPacket(SystemMessageId.YOU_CANNOT_TELEPORT_RIGHT_NOW);
				}
			}
		}
	}
}
