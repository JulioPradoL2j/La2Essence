package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Map;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.ClanEntryManager;
import org.l2jmobius.gameserver.model.clan.entry.PledgeApplicantInfo;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPledgeWaitingList extends ServerPacket
{
	private final Map<Integer, PledgeApplicantInfo> _pledgePlayerRecruitInfos;

	public ExPledgeWaitingList(int clanId)
	{
		this._pledgePlayerRecruitInfos = ClanEntryManager.getInstance().getApplicantListForClan(clanId);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_WAITING_LIST.writeId(this, buffer);
		buffer.writeInt(this._pledgePlayerRecruitInfos.size());

		for (PledgeApplicantInfo recruitInfo : this._pledgePlayerRecruitInfos.values())
		{
			buffer.writeInt(recruitInfo.getPlayerId());
			buffer.writeString(recruitInfo.getPlayerName());
			buffer.writeInt(recruitInfo.getClassId());
			buffer.writeInt(recruitInfo.getPlayerLvl());
		}
	}
}
