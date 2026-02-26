package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.ClanMember;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PledgeReceivePowerInfo extends ServerPacket
{
	private final ClanMember _member;

	public PledgeReceivePowerInfo(ClanMember member)
	{
		this._member = member;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_VIEW_PLEDGE_POWER.writeId(this, buffer);
		buffer.writeInt(this._member.getPowerGrade());
		buffer.writeString(this._member.getName());
		buffer.writeInt(this._member.getClan().getRankPrivs(this._member.getPowerGrade()).getMask());
	}
}
