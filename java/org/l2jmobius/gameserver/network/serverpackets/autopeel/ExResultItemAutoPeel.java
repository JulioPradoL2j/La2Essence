package org.l2jmobius.gameserver.network.serverpackets.autopeel;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExResultItemAutoPeel extends ServerPacket
{
	private final boolean _result;
	private final long _totalPeelCount;
	private final long _remainingPeelCount;
	private final Collection<ItemHolder> _itemList;

	public ExResultItemAutoPeel(boolean result, long totalPeelCount, long remainingPeelCount, Collection<ItemHolder> itemList)
	{
		this._result = result;
		this._totalPeelCount = totalPeelCount;
		this._remainingPeelCount = remainingPeelCount;
		this._itemList = itemList;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESULT_ITEM_AUTO_PEEL.writeId(this, buffer);
		buffer.writeByte(this._result);
		buffer.writeLong(this._totalPeelCount);
		buffer.writeLong(this._remainingPeelCount);
		buffer.writeInt(this._itemList.size());

		for (ItemHolder holder : this._itemList)
		{
			buffer.writeInt(holder.getId());
			buffer.writeLong(holder.getCount());
			buffer.writeInt(0);
			buffer.writeByte(0);
			buffer.writeByte(0);
		}
	}
}
