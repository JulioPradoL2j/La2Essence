package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.entry.PledgeApplicantInfo;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPledgeWaitingUser extends ServerPacket
{
	private final PledgeApplicantInfo _pledgeRecruitInfo;

	public ExPledgeWaitingUser(PledgeApplicantInfo pledgeRecruitInfo)
	{
		this._pledgeRecruitInfo = pledgeRecruitInfo;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_WAITING_USER.writeId(this, buffer);
		buffer.writeInt(this._pledgeRecruitInfo.getPlayerId());
		buffer.writeString(this._pledgeRecruitInfo.getMessage());
	}
}
