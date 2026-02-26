package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.Party;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExMPCCShowPartyMemberInfo extends ServerPacket
{
	private final Party _party;

	public ExMPCCShowPartyMemberInfo(Party party)
	{
		this._party = party;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MPCC_SHOW_PARTY_MEMBERS_INFO.writeId(this, buffer);
		buffer.writeInt(this._party.getMemberCount());

		for (Player pc : this._party.getMembers())
		{
			buffer.writeString(pc.getName());
			buffer.writeInt(pc.getObjectId());
			buffer.writeInt(pc.getPlayerClass().getId());
		}
	}
}
