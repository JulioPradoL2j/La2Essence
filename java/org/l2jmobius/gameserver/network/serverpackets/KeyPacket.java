package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.ServerConfig;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class KeyPacket extends ServerPacket
{
	private final byte[] _key;
	private final int _result;

	public KeyPacket(byte[] key, int result)
	{
		this._key = key;
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.VERSION_CHECK.writeId(this, buffer);
		buffer.writeByte(this._result);

		for (int i = 0; i < 8; i++)
		{
			buffer.writeByte(this._key[i]);
		}

		buffer.writeInt(ServerConfig.PACKET_ENCRYPTION);
		buffer.writeInt(ServerConfig.SERVER_ID);
		buffer.writeByte(1);
		buffer.writeInt(0);
		if ((ServerConfig.SERVER_LIST_TYPE & 4096) == 4096 || (ServerConfig.SERVER_LIST_TYPE & 8192) == 8192)
		{
			buffer.writeByte(4);
		}
		else if ((ServerConfig.SERVER_LIST_TYPE & 1024) == 1024)
		{
			buffer.writeByte(1);
		}
		else
		{
			buffer.writeByte(0);
		}

		buffer.writeByte(0);
		buffer.writeByte(0);
	}
}
