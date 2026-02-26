package org.l2jmobius.gameserver.network.serverpackets.adenlab;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAdenLabNormalSlot extends ServerPacket
{
	private final int _bossId;
	private final int _currentSlot;
	private final int _remainingCards;

	public ExAdenLabNormalSlot(int bossId, int currentSlot, int remainingCards)
	{
		this._bossId = bossId;
		this._currentSlot = currentSlot;
		this._remainingCards = remainingCards;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ADENLAB_NORMAL_SLOT.writeId(this, buffer);
		buffer.writeInt(this._bossId);
		buffer.writeInt(this._currentSlot);
		buffer.writeInt(this._remainingCards);
	}
}
