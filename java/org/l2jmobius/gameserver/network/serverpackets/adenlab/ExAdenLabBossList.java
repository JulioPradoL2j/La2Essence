package org.l2jmobius.gameserver.network.serverpackets.adenlab;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAdenLabBossList extends ServerPacket
{
	private final List<Integer> _bossList = new ArrayList<>();

	public ExAdenLabBossList(List<Integer> bossList)
	{
		this._bossList.addAll(bossList);
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ADENLAB_BOSS_LIST.writeId(this, buffer);
		buffer.writeInt(this._bossList.size());

		for (int value : this._bossList)
		{
			buffer.writeInt(value);
		}
	}
}
