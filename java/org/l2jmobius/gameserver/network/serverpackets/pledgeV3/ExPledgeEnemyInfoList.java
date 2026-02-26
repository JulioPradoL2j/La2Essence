package org.l2jmobius.gameserver.network.serverpackets.pledgeV3;

import java.util.List;
import java.util.stream.Collectors;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.clan.ClanWar;
import org.l2jmobius.gameserver.model.clan.enums.ClanWarState;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPledgeEnemyInfoList extends ServerPacket
{
	private final Clan _playerClan;
	private final List<ClanWar> _warList;

	public ExPledgeEnemyInfoList(Clan playerClan)
	{
		this._playerClan = playerClan;
		this._warList = playerClan.getWarList().values().stream().filter(it -> it.getClanWarState(playerClan) == ClanWarState.MUTUAL || it.getAttackerClanId() == playerClan.getId()).collect(Collectors.toList());
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_ENEMY_INFO_LIST.writeId(this, buffer);
		buffer.writeInt(this._warList.size());

		for (ClanWar war : this._warList)
		{
			Clan clan = war.getOpposingClan(this._playerClan);
			buffer.writeInt(clan.getRank());
			buffer.writeInt(clan.getId());
			buffer.writeSizedString(clan.getName());
			buffer.writeSizedString(clan.getLeaderName());
			buffer.writeInt((int) (war.getStartTime() / 1000L));
		}
	}
}
