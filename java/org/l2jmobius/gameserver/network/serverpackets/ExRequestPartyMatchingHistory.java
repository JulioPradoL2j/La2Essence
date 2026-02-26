package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.sql.PartyMatchingHistoryTable;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoomHistory;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExRequestPartyMatchingHistory extends ServerPacket
{
	private final Collection<MatchingRoomHistory> _history = PartyMatchingHistoryTable.getInstance().getHistory();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PARTY_MATCHING_ROOM_HISTORY.writeId(this, buffer);
		buffer.writeInt(this._history.size());

		for (MatchingRoomHistory holder : this._history)
		{
			buffer.writeString(holder.getTitle());
			buffer.writeString(holder.getLeader());
		}
	}
}
