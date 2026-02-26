package org.l2jmobius.gameserver.network.serverpackets.adenlab;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAdenLabTranscendEnchant extends ServerPacket
{
	private final int _bossId;
	private final byte _success;

	public ExAdenLabTranscendEnchant(int bossID, byte success)
	{
		this._bossId = bossID;
		this._success = success;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ADENLAB_TRANSCEND_ENCHANT.writeId(this, buffer);
		buffer.writeInt(this._bossId);
		buffer.writeByte(this._success);
	}
}
