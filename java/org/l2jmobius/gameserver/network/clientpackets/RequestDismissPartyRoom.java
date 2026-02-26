package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoom;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoomType;

public class RequestDismissPartyRoom extends ClientPacket
{
	private int _roomid;

	@Override
	protected void readImpl()
	{
		this._roomid = this.readInt();
		this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			MatchingRoom room = player.getMatchingRoom();
			if (room != null && room.getId() == this._roomid && room.getRoomType() == MatchingRoomType.PARTY && room.getLeader() == player)
			{
				room.disbandRoom();
			}
		}
	}
}
