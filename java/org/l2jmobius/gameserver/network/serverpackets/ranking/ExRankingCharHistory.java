package org.l2jmobius.gameserver.network.serverpackets.ranking;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.RankingHistoryDataHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRankingCharHistory extends ServerPacket
{
	private final Player _player;
	private final Collection<RankingHistoryDataHolder> _history;

	public ExRankingCharHistory(Player player)
	{
		this._player = player;
		this._history = this._player.getRankingHistoryData();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RANKING_CHAR_HISTORY.writeId(this, buffer);
		buffer.writeInt(this._history.size());
		if (this._history.isEmpty())
		{
			buffer.writeByte(0);
			buffer.writeByte(0);
			buffer.writeByte(0);
		}
		else
		{
			for (RankingHistoryDataHolder rankingData : this._history)
			{
				buffer.writeInt((int) rankingData.getDay());
				buffer.writeInt(rankingData.getRank());
				buffer.writeLong(rankingData.getExp());
			}
		}
	}
}
