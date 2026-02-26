package org.l2jmobius.gameserver.network.serverpackets.adenlab;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAdenLabBossUnlock extends ServerPacket
{
	private final int _bossId;
	private final boolean _success;

	public ExAdenLabBossUnlock(int bossId, boolean success)
	{
		this._bossId = bossId;
		this._success = success;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ADENLAB_UNLOCK_BOSS.writeId(this, buffer);
		buffer.writeInt(this._bossId);
		buffer.writeByte((byte) (this._success ? 1 : 0));
	}
}
