package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExChangeNicknameEmote extends ServerPacket
{
	private final int _itemId;

	public ExChangeNicknameEmote(int itemId)
	{
		this._itemId = itemId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHANGE_NICKNAME_COLOR_ICON.writeId(this, buffer);
		buffer.writeInt(this._itemId);
	}
}
