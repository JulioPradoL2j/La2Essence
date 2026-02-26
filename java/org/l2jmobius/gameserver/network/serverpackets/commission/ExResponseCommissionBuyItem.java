package org.l2jmobius.gameserver.network.serverpackets.commission;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.ItemInfo;
import org.l2jmobius.gameserver.model.commission.CommissionItem;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExResponseCommissionBuyItem extends ServerPacket
{
	public static final ExResponseCommissionBuyItem FAILED = new ExResponseCommissionBuyItem(null);
	private final CommissionItem _commissionItem;

	public ExResponseCommissionBuyItem(CommissionItem commissionItem)
	{
		this._commissionItem = commissionItem;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESPONSE_COMMISSION_BUY_ITEM.writeId(this, buffer);
		buffer.writeInt(this._commissionItem != null);
		if (this._commissionItem != null)
		{
			ItemInfo itemInfo = this._commissionItem.getItemInfo();
			buffer.writeInt(itemInfo.getEnchantLevel());
			buffer.writeInt(itemInfo.getItem().getId());
			buffer.writeLong(itemInfo.getCount());
		}
	}
}
