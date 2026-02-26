package org.l2jmobius.gameserver.network.serverpackets;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.entry.PledgeWaitingInfo;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPledgeDraftListSearch extends ServerPacket
{
	final List<PledgeWaitingInfo> _pledgeRecruitList;

	public ExPledgeDraftListSearch(List<PledgeWaitingInfo> pledgeRecruitList)
	{
		this._pledgeRecruitList = pledgeRecruitList;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_DRAFT_LIST_SEARCH.writeId(this, buffer);
		buffer.writeInt(this._pledgeRecruitList.size());

		for (PledgeWaitingInfo prl : this._pledgeRecruitList)
		{
			buffer.writeInt(prl.getPlayerId());
			buffer.writeString(prl.getPlayerName());
			buffer.writeInt(prl.getKarma());
			buffer.writeInt(prl.getPlayerClassId());
			buffer.writeInt(prl.getPlayerLvl());
		}
	}
}
