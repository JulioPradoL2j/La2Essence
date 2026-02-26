package org.l2jmobius.gameserver.network.clientpackets;

import java.util.Arrays;

import org.l2jmobius.gameserver.data.xml.ActionData;
import org.l2jmobius.gameserver.handler.IPlayerActionHandler;
import org.l2jmobius.gameserver.handler.PlayerActionHandler;
import org.l2jmobius.gameserver.model.ActionDataHolder;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.PrivateStoreType;
import org.l2jmobius.gameserver.model.actor.transform.Transform;
import org.l2jmobius.gameserver.model.actor.transform.TransformTemplate;
import org.l2jmobius.gameserver.model.effects.AbstractEffect;
import org.l2jmobius.gameserver.model.skill.AbnormalType;
import org.l2jmobius.gameserver.model.skill.BuffInfo;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import org.l2jmobius.gameserver.network.serverpackets.RecipeShopManageList;

public class RequestActionUse extends ClientPacket
{
	private int _actionId;
	private boolean _ctrlPressed;
	private boolean _shiftPressed;

	@Override
	protected void readImpl()
	{
		this._actionId = this.readInt();
		this._ctrlPressed = this.readInt() == 1;
		this._shiftPressed = this.readByte() == 1;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if ((!player.isFakeDeath() || this._actionId == 0) && !player.isDead() && !player.isControlBlocked())
			{
				BuffInfo info = player.getEffectList().getFirstBuffInfoByAbnormalType(AbnormalType.BOT_PENALTY);
				if (info != null)
				{
					for (AbstractEffect effect : info.getEffects())
					{
						if (!effect.checkCondition(this._actionId))
						{
							player.sendPacket(SystemMessageId.YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_YOUR_ACTIONS_HAVE_BEEN_RESTRICTED);
							player.sendPacket(ActionFailed.STATIC_PACKET);
							return;
						}
					}
				}

				Transform transform = player.getTransformation();
				if (transform != null)
				{
					TransformTemplate transformTemplate = transform.getTemplate(player);
					int[] allowedActions = transformTemplate.getBasicActionList();
					if (allowedActions == null || Arrays.binarySearch(allowedActions, this._actionId) < 0)
					{
						player.sendPacket(ActionFailed.STATIC_PACKET);
						PacketLogger.warning(player + " used action which he does not have! Id = " + this._actionId + " transform: " + transform.getId());
						return;
					}
				}

				ActionDataHolder actionHolder = ActionData.getInstance().getActionData(this._actionId);
				if (actionHolder != null)
				{
					IPlayerActionHandler actionHandler = PlayerActionHandler.getInstance().getHandler(actionHolder.getHandler());
					if (actionHandler != null)
					{
						actionHandler.onAction(player, actionHolder, this._ctrlPressed, this._shiftPressed);
					}
					else
					{
						PacketLogger.warning("Couldn't find handler with name: " + actionHolder.getHandler());
					}
				}
				else
				{
					switch (this._actionId)
					{
						case 51:
							if (player.isAlikeDead() || player.isSellingBuffs())
							{
								player.sendPacket(ActionFailed.STATIC_PACKET);
								return;
							}

							if (player.isInStoreMode())
							{
								player.setPrivateStoreType(PrivateStoreType.NONE);
								player.broadcastUserInfo();
							}

							if (player.isSitting())
							{
								player.standUp();
							}

							player.sendPacket(new RecipeShopManageList(player, false));
							break;
						default:
							PacketLogger.warning(player.getName() + ": unhandled action type " + this._actionId);
					}
				}
			}
			else
			{
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
	}
}
