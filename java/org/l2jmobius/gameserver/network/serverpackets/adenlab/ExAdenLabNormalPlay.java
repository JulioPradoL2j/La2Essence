package org.l2jmobius.gameserver.network.serverpackets.adenlab;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAdenLabNormalPlay extends ServerPacket
{
	private final int _bossId;
	private final int _pageIndex;
	private final int _result;

	public ExAdenLabNormalPlay(int bossId, int currentSlot, byte result)
	{
		this._bossId = bossId;
		this._pageIndex = currentSlot;
		this._result = result;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ADENLAB_NORMAL_PLAY.writeId(this, buffer);
		buffer.writeInt(this._bossId);
		buffer.writeInt(this._pageIndex);
		buffer.writeInt(this._result);
	}
}
