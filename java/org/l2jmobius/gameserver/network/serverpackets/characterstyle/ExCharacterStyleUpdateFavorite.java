package org.l2jmobius.gameserver.network.serverpackets.characterstyle;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCharacterStyleUpdateFavorite extends ServerPacket
{
	public static final ExCharacterStyleUpdateFavorite STATIC_PACKET_UPDATE = new ExCharacterStyleUpdateFavorite((byte) 1);
	private final byte _result;

	public ExCharacterStyleUpdateFavorite(byte result)
	{
		this._result = result;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHARACTER_STYLE_UPDATE_FAVORITE.writeId(this, buffer);
		buffer.writeByte(this._result);
	}
}
