package org.l2jmobius.gameserver.network.clientpackets.commission;

import org.l2jmobius.gameserver.managers.ItemCommissionManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.commission.CommissionItem;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.commission.ExCloseCommission;
import org.l2jmobius.gameserver.network.serverpackets.commission.ExResponseCommissionBuyInfo;

public class RequestCommissionBuyInfo extends ClientPacket
{
	private long _commissionId;

	@Override
	protected void readImpl()
	{
		this._commissionId = this.readLong();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (!ItemCommissionManager.isPlayerAllowedToInteract(player))
			{
				player.sendPacket(ExCloseCommission.STATIC_PACKET);
			}
			else if (player.isInventoryUnder80(false) && player.getWeightPenalty() < 3)
			{
				CommissionItem commissionItem = ItemCommissionManager.getInstance().getCommissionItem(this._commissionId);
				if (commissionItem != null)
				{
					player.sendPacket(new ExResponseCommissionBuyInfo(commissionItem));
				}
				else
				{
					player.sendPacket(SystemMessageId.ITEM_PURCHASE_IS_NOT_AVAILABLE_BECAUSE_THE_CORRESPONDING_ITEM_DOES_NOT_EXIST);
					player.sendPacket(ExResponseCommissionBuyInfo.FAILED);
				}
			}
			else
			{
				player.sendPacket(SystemMessageId.TO_BUY_CANCEL_YOU_NEED_TO_FREE_20_OF_WEIGHT_AND_10_OF_SLOTS_IN_YOUR_INVENTORY);
				player.sendPacket(ExResponseCommissionBuyInfo.FAILED);
			}
		}
	}
}
