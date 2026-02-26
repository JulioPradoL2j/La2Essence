package org.l2jmobius.gameserver.network.serverpackets.blessing;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExBlessOptionProbList extends ServerPacket
{
	private final int _scrollId;
	private final int _itemId;

	public ExBlessOptionProbList(int scrollId, int itemId)
	{
		this._scrollId = scrollId;
		this._itemId = itemId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BLESS_OPTION_PROB_LIST.writeId(this, buffer);
		buffer.writeInt(this._scrollId);
		buffer.writeInt(this._itemId);
		buffer.writeInt(0);
		buffer.writeInt(0);
	}
}
