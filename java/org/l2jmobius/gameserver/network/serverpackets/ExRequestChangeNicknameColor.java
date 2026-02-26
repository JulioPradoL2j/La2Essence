package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExRequestChangeNicknameColor extends ServerPacket
{
	private final int _itemId;

	public ExRequestChangeNicknameColor(int itemId)
	{
		this._itemId = itemId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHANGE_NICKNAME_COLOR.writeId(this, buffer);
		buffer.writeInt(this._itemId);
	}
}
