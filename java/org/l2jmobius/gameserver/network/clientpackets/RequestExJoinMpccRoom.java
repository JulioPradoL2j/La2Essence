package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.MatchingRoomManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoom;

public class RequestExJoinMpccRoom extends ClientPacket
{
	private int _roomId;

	@Override
	protected void readImpl()
	{
		this._roomId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && player.getMatchingRoom() == null)
		{
			MatchingRoom room = MatchingRoomManager.getInstance().getCCMatchingRoom(this._roomId);
			if (room != null)
			{
				room.addMember(player);
			}
		}
	}
}
