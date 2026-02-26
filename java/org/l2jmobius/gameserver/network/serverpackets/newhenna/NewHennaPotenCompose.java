package org.l2jmobius.gameserver.network.serverpackets.newhenna;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class NewHennaPotenCompose extends ServerPacket
{
	private final int _resultHennaId;
	private final int _resultItemId;
	private final boolean _success;

	public NewHennaPotenCompose(int resultHennaId, int resultItemId, boolean success)
	{
		this._resultHennaId = resultHennaId;
		this._resultItemId = resultItemId;
		this._success = success;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_NEW_HENNA_COMPOSE.writeId(this, buffer);
		buffer.writeInt(this._resultHennaId);
		buffer.writeInt(this._resultItemId);
		buffer.writeByte(this._success);
	}
}
