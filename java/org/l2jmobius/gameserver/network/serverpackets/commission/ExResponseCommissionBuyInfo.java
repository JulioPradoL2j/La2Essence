package org.l2jmobius.gameserver.network.serverpackets.commission;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.commission.CommissionItem;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.AbstractItemPacket;

public class ExResponseCommissionBuyInfo extends AbstractItemPacket
{
	public static final ExResponseCommissionBuyInfo FAILED = new ExResponseCommissionBuyInfo(null);
	private final CommissionItem _commissionItem;

	public ExResponseCommissionBuyInfo(CommissionItem commissionItem)
	{
		this._commissionItem = commissionItem;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESPONSE_COMMISSION_BUY_INFO.writeId(this, buffer);
		buffer.writeInt(this._commissionItem != null);
		if (this._commissionItem != null)
		{
			buffer.writeLong(this._commissionItem.getPricePerUnit());
			buffer.writeLong(this._commissionItem.getCommissionId());
			buffer.writeInt(0);
			this.writeItem(this._commissionItem.getItemInfo(), buffer);
		}
	}
}
