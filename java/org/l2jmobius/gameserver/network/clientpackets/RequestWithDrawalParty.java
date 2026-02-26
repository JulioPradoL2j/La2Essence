package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.Party;
import org.l2jmobius.gameserver.model.groups.PartyMessageType;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoom;

public class RequestWithDrawalParty extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Party party = player.getParty();
			if (party != null)
			{
				party.removePartyMember(player, PartyMessageType.LEFT);
				MatchingRoom room = player.getMatchingRoom();
				if (room != null)
				{
					room.deleteMember(player, false);
				}
			}
		}
	}
}
