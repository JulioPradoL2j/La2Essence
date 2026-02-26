package org.l2jmobius.gameserver.network.serverpackets.blackcoupon;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExItemRestoreOpen extends ServerPacket
{
	private final int _itemId;

	public ExItemRestoreOpen(int itemId)
	{
		this._itemId = itemId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ITEM_RESTORE_OPEN.writeId(this, buffer);
		buffer.writeInt(this._itemId);
	}
}
