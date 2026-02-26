package org.l2jmobius.gameserver.network.serverpackets.pledgedonation;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.ClanMember;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPledgeContributionList extends ServerPacket
{
	private final Collection<ClanMember> _contributors;

	public ExPledgeContributionList(Collection<ClanMember> contributors)
	{
		this._contributors = contributors;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_CONTRIBUTION_LIST.writeId(this, buffer);
		buffer.writeInt(this._contributors.size());

		for (ClanMember contributor : this._contributors)
		{
			buffer.writeSizedString(contributor.getName());
			buffer.writeInt(contributor.getClan().getClanContributionWeekly(contributor.getObjectId()));
			buffer.writeInt(contributor.getClan().getClanContribution(contributor.getObjectId()));
		}
	}
}
