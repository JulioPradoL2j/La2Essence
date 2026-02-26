package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.MatchingRoomManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoom;

public class RequestPartyMatchDetail extends ClientPacket
{
	private int _roomId;
	private int _location;
	private int _level;

	@Override
	protected void readImpl()
	{
		this._roomId = this.readInt();
		this._location = this.readInt();
		this._level = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (!player.isInMatchingRoom())
			{
				MatchingRoom room = this._roomId > 0 ? MatchingRoomManager.getInstance().getPartyMathchingRoom(this._roomId) : MatchingRoomManager.getInstance().getPartyMathchingRoom(this._location, this._level);
				if (room != null)
				{
					room.addMember(player);
				}
			}
		}
	}
}
