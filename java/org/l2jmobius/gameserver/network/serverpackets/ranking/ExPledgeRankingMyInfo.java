package org.l2jmobius.gameserver.network.serverpackets.ranking;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.RankManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPledgeRankingMyInfo extends ServerPacket
{
	private final Player _player;

	public ExPledgeRankingMyInfo(Player player)
	{
		this._player = player;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_RANKING_MY_INFO.writeId(this, buffer);
		Clan clan = this._player.getClan();
		buffer.writeInt(clan != null ? (RankManager.getInstance().getClanRankList().entrySet().stream().anyMatch(it -> it.getValue().getInt("clan_id") == this._player.getClanId()) ? RankManager.getInstance().getClanRankList().entrySet().stream().filter(it -> it.getValue().getInt("clan_id") == this._player.getClanId()).findFirst().orElse(null).getKey() : 0) : 0);
		buffer.writeInt(clan != null ? (RankManager.getInstance().getSnapshotClanRankList().entrySet().stream().anyMatch(it -> it.getValue().getInt("clan_id") == this._player.getClanId()) ? RankManager.getInstance().getSnapshotClanRankList().entrySet().stream().filter(it -> it.getValue().getInt("clan_id") == this._player.getClanId()).findFirst().orElse(null).getKey() : 0) : 0);
		buffer.writeInt(clan != null ? clan.getExp() : 0);
	}
}
