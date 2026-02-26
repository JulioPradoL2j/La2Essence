package org.l2jmobius.gameserver.network.serverpackets.adenlab;

import java.util.Map;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAdenLabSpecialSlot extends ServerPacket
{
	private final int _bossId;
	private final int _pageIndex;
	private final Map<Byte, Integer> _drawnOptionGrades;
	private final Map<Byte, Integer> _fixedOptionGrades;

	public ExAdenLabSpecialSlot(int bossId, int slotId, Map<Byte, Integer> drawnOptionGrades, Map<Byte, Integer> fixedOptionGrades)
	{
		this._bossId = bossId;
		this._pageIndex = slotId;
		this._drawnOptionGrades = drawnOptionGrades;
		this._fixedOptionGrades = fixedOptionGrades;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ADENLAB_SPECIAL_SLOT.writeId(this, buffer);
		buffer.writeInt(this._bossId);
		buffer.writeInt(this._pageIndex);
		buffer.writeInt(this._drawnOptionGrades.size());

		for (int optionGrades : this._drawnOptionGrades.values())
		{
			buffer.writeInt(optionGrades);
		}

		buffer.writeInt(this._fixedOptionGrades.size());

		for (int optionGrades : this._fixedOptionGrades.values())
		{
			buffer.writeInt(optionGrades);
		}
	}
}
