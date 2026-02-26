package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.enums.ClanEntryStatus;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPledgeRecruitApplyInfo extends ServerPacket
{
	private final ClanEntryStatus _status;

	public ExPledgeRecruitApplyInfo(ClanEntryStatus status)
	{
		this._status = status;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_RECRUIT_APPLY_INFO.writeId(this, buffer);
		buffer.writeInt(this._status.ordinal());
	}
}
