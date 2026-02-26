package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.effects.AbstractEffect;
import org.l2jmobius.gameserver.model.skill.AbnormalType;
import org.l2jmobius.gameserver.model.skill.BuffInfo;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;

public class AttackRequest extends ClientPacket
{
	protected int _objectId;
	protected int _originX;
	protected int _originY;
	protected int _originZ;
	protected int _attackId;

	@Override
	protected void readImpl()
	{
		this._objectId = this.readInt();
		this._originX = this.readInt();
		this._originY = this.readInt();
		this._originZ = this.readInt();
		this._attackId = this.readByte();
	}

	@Override
	protected void runImpl()
	{
		if (this.getClient().getFloodProtectors().canPerformPlayerAction())
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				if (player.isPlayable() && player.isInBoat())
				{
					player.sendPacket(SystemMessageId.UNAVAILABLE_WHILE_SWIMMING);
					player.sendPacket(ActionFailed.STATIC_PACKET);
				}
				else
				{
					BuffInfo info = player.getEffectList().getFirstBuffInfoByAbnormalType(AbnormalType.BOT_PENALTY);
					if (info != null)
					{
						for (AbstractEffect effect : info.getEffects())
						{
							if (!effect.checkCondition(-1))
							{
								player.sendPacket(SystemMessageId.YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_YOUR_ACTIONS_HAVE_BEEN_RESTRICTED);
								player.sendPacket(ActionFailed.STATIC_PACKET);
								return;
							}
						}
					}

					WorldObject target;
					if (player.getTargetId() == this._objectId)
					{
						target = player.getTarget();
					}
					else
					{
						target = World.getInstance().findObject(this._objectId);
					}

					if (target == null)
					{
						player.sendPacket(ActionFailed.STATIC_PACKET);
					}
					else if ((!target.isTargetable() || player.isTargetingDisabled()) && !player.isGM())
					{
						player.sendPacket(ActionFailed.STATIC_PACKET);
					}
					else if (target.getInstanceWorld() != player.getInstanceWorld())
					{
						player.sendPacket(ActionFailed.STATIC_PACKET);
					}
					else if (!target.isVisibleFor(player))
					{
						player.sendPacket(ActionFailed.STATIC_PACKET);
					}
					else
					{
						player.onActionRequest();
						if (player.getTarget() != target)
						{
							target.onAction(player);
						}
						else if (target.getObjectId() != player.getObjectId() && !player.isInStoreMode() && player.getActiveRequester() == null)
						{
							target.onForcedAttack(player);
						}
						else
						{
							player.sendPacket(ActionFailed.STATIC_PACKET);
						}
					}
				}
			}
		}
	}
}
