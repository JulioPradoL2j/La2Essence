package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.ClanMember;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PledgeReceiveMemberInfo extends ServerPacket
{
	private final ClanMember _member;

	public PledgeReceiveMemberInfo(ClanMember member)
	{
		this._member = member;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_VIEW_PLEDGE_MEMBER_INFO.writeId(this, buffer);
		buffer.writeInt(this._member.getPledgeType());
		buffer.writeString(this._member.getName());
		buffer.writeString(this._member.getTitle());
		buffer.writeInt(this._member.getPowerGrade());
		if (this._member.getPledgeType() != 0)
		{
			buffer.writeString(this._member.getClan().getSubPledge(this._member.getPledgeType()).getName());
		}
		else
		{
			buffer.writeString(this._member.getClan().getName());
		}

		buffer.writeString(this._member.getApprenticeOrSponsorName());
	}
}
