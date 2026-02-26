package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.ClanEntryManager;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.clan.entry.PledgeApplicantInfo;
import org.l2jmobius.gameserver.model.clan.entry.PledgeRecruitInfo;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPledgeWaitingListApplied extends ServerPacket
{
	private final PledgeApplicantInfo _pledgePlayerRecruitInfo;
	private final PledgeRecruitInfo _pledgeRecruitInfo;

	public ExPledgeWaitingListApplied(int clanId, int playerId)
	{
		this._pledgePlayerRecruitInfo = ClanEntryManager.getInstance().getPlayerApplication(clanId, playerId);
		this._pledgeRecruitInfo = ClanEntryManager.getInstance().getClanById(clanId);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_WAITING_LIST_APPLIED.writeId(this, buffer);
		Clan clan = this._pledgeRecruitInfo.getClan();
		buffer.writeInt(clan.getId());
		buffer.writeString(clan.getName());
		buffer.writeString(clan.getLeaderName());
		buffer.writeInt(clan.getLevel());
		buffer.writeInt(clan.getMembersCount());
		buffer.writeInt(this._pledgeRecruitInfo.getKarma());
		buffer.writeString(this._pledgeRecruitInfo.getInformation());
		buffer.writeString(this._pledgePlayerRecruitInfo.getMessage());
	}
}
