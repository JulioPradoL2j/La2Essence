package org.l2jmobius.gameserver.network.clientpackets;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.Party;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoom;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoomType;
import org.l2jmobius.gameserver.network.serverpackets.ExMPCCPartymasterList;

public class RequestExMpccPartymasterList extends ClientPacket
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
			MatchingRoom room = player.getMatchingRoom();
			if (room != null && room.getRoomType() == MatchingRoomType.COMMAND_CHANNEL)
			{
				Set<String> leadersName = room.getMembers().stream().map(Player::getParty).filter(Objects::nonNull).map(Party::getLeader).map(Player::getName).collect(Collectors.toSet());
				player.sendPacket(new ExMPCCPartymasterList(leadersName));
			}
		}
	}
}
