package org.l2jmobius.gameserver.network.serverpackets.blessing;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.RatesConfig;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExOpenBlessOptionScroll extends ServerPacket
{
	private final int _itemId;

	public ExOpenBlessOptionScroll(int itemId)
	{
		this._itemId = itemId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_OPEN_BLESS_OPTION_SCROLL.writeId(this, buffer);
		buffer.writeInt(this._itemId);
		buffer.writeInt((int) (RatesConfig.BLESSING_CHANCE * 100000.0));
	}
}
