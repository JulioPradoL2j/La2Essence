package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Set;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.sql.CharInfoTable;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class BlockListPacket extends ServerPacket
{
	private final Set<Integer> _playerIds;

	public BlockListPacket(Set<Integer> playerIds)
	{
		this._playerIds = playerIds;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.BLOCK_PACKET_LIST.writeId(this, buffer);
		buffer.writeInt(this._playerIds.size());

		for (int playerId : this._playerIds)
		{
			buffer.writeString(CharInfoTable.getInstance().getNameById(playerId));
			buffer.writeString("");
		}
	}
}
