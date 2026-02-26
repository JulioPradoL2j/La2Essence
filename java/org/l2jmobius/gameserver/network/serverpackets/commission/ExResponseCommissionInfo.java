package org.l2jmobius.gameserver.network.serverpackets.commission;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExResponseCommissionInfo extends ServerPacket
{
	public static final ExResponseCommissionInfo EMPTY = new ExResponseCommissionInfo();
	private final int _result;
	private final int _itemId;
	private final long _presetPricePerUnit;
	private final long _presetAmount;
	private final int _presetDurationType;

	private ExResponseCommissionInfo()
	{
		this._result = 0;
		this._itemId = 0;
		this._presetPricePerUnit = 0L;
		this._presetAmount = 0L;
		this._presetDurationType = -1;
	}

	public ExResponseCommissionInfo(int itemId, long presetPricePerUnit, long presetAmount, int presetDurationType)
	{
		this._result = 1;
		this._itemId = itemId;
		this._presetPricePerUnit = presetPricePerUnit;
		this._presetAmount = presetAmount;
		this._presetDurationType = presetDurationType;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESPONSE_COMMISSION_INFO.writeId(this, buffer);
		buffer.writeInt(this._result);
		buffer.writeInt(this._itemId);
		buffer.writeLong(this._presetPricePerUnit);
		buffer.writeLong(this._presetAmount);
		buffer.writeInt(this._presetDurationType);
	}
}
