package org.l2jmobius.gameserver.network.serverpackets.blackcoupon;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ItemRestoreResult extends ServerPacket
{
	public static final ItemRestoreResult FAIL = new ItemRestoreResult(1);
	public static final ItemRestoreResult SUCCESS = new ItemRestoreResult(0);
	private final int _type;

	public ItemRestoreResult(int type)
	{
		this._type = type;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ITEM_RESTORE.writeId(this, buffer);
		buffer.writeByte(this._type);
	}
}
