package org.l2jmobius.gameserver.network.serverpackets.friend;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class FriendRemove extends ServerPacket
{
	private final int _responce;
	private final String _charName;

	public FriendRemove(String charName, int responce)
	{
		this._responce = responce;
		this._charName = charName;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.FRIEND_REMOVE.writeId(this, buffer);
		buffer.writeInt(this._responce);
		buffer.writeString(this._charName);
	}
}
