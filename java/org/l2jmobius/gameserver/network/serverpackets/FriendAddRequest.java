package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class FriendAddRequest extends ServerPacket
{
	private final String _requestorName;

	public FriendAddRequest(String requestorName)
	{
		this._requestorName = requestorName;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.FRIEND_ADD_REQUEST.writeId(this, buffer);
		buffer.writeByte(1);
		buffer.writeString(this._requestorName);
	}
}
