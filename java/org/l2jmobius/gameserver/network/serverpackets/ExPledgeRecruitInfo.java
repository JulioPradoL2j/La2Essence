package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.sql.ClanTable;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPledgeRecruitInfo extends ServerPacket
{
	private final Clan _clan;

	public ExPledgeRecruitInfo(int clanId)
	{
		this._clan = ClanTable.getInstance().getClan(clanId);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_RECRUIT_INFO.writeId(this, buffer);
		Collection<Clan.SubPledge> subPledges = this._clan.getAllSubPledges();
		buffer.writeString(this._clan.getName());
		buffer.writeString(this._clan.getLeaderName());
		buffer.writeInt(this._clan.getLevel());
		buffer.writeInt(this._clan.getMembersCount());
		buffer.writeInt(subPledges.size());

		for (Clan.SubPledge subPledge : subPledges)
		{
			buffer.writeInt(subPledge.getId());
			buffer.writeString(subPledge.getName());
		}
	}
}
