package org.l2jmobius.gameserver.network.clientpackets.relics;

import java.util.List;

import org.l2jmobius.gameserver.config.RelicSystemConfig;
import org.l2jmobius.gameserver.data.holders.RelicCouponHolder;
import org.l2jmobius.gameserver.data.xml.RelicCouponData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.RelicSummonRequest;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.relics.ExRelicsList;
import org.l2jmobius.gameserver.network.serverpackets.relics.ExRelicsSummonResult;

public class RequestRelicsSummon extends ClientPacket
{
	private int _couponId;

	@Override
	protected void readImpl()
	{
		this._couponId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			RelicCouponHolder relicCoupon = RelicCouponData.getInstance().getCouponFromCouponItemId(this._couponId);
			if (relicCoupon == null)
			{
				player.sendPacket(SystemMessageId.AN_ERROR_HAS_OCCURRED_PLEASE_TRY_AGAIN_LATER);
				PacketLogger.finer("Relic Coupon: " + this._couponId + " wasn't found in RelicCouponData.xml");
			}
			else if (player.getAccountVariables().getInt("UNCONFIRMED_RELICS_COUNT", 0) == RelicSystemConfig.RELIC_UNCONFIRMED_LIST_LIMIT)
			{
				player.sendPacket(SystemMessageId.SUMMON_COMPOUND_IS_UNAVAILABLE_AS_YOU_HAVE_MORE_THAN_100_UNCONFIRMED_DOLLS);
			}
			else
			{
				Item couponItem = player.getInventory().getItemByItemId(this._couponId);
				if (couponItem != null && player.destroyItem(ItemProcessType.FEE, couponItem, 1L, player, true))
				{
					List<Integer> obtainedRelics = RelicCouponData.getInstance().generateSummonRelics(relicCoupon);
					if (obtainedRelics.isEmpty())
					{
						player.sendPacket(SystemMessageId.AN_ERROR_HAS_OCCURRED_PLEASE_TRY_AGAIN_LATER);
						PacketLogger.finer("Relic Coupon: " + this._couponId + " generated 0 relics!");
					}
					else
					{
						for (int relicId : obtainedRelics)
						{
							player.handleRelicAcquisition(relicId);
							if (RelicSystemConfig.RELIC_SYSTEM_DEBUG_ENABLED)
							{
								player.sendMessage("Summoned relic ID: " + relicId);
							}
						}

						player.sendPacket(new ExRelicsList(player));
						player.sendCombatPower();
						player.addRequest(new RelicSummonRequest(player));
						player.sendPacket(new ExRelicsSummonResult(relicCoupon, obtainedRelics));
					}
				}
				else
				{
					player.sendPacket(SystemMessageId.FAILURE_ALL_ITEMS_HAVE_DISAPPEARED);
				}
			}
		}
	}
}
