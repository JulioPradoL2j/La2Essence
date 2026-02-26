package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoom;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoomType;
import org.l2jmobius.gameserver.model.groups.matching.PartyMatchingRoom;
import org.l2jmobius.gameserver.network.serverpackets.PartyRoomInfo;

public class RequestPartyMatchList extends ClientPacket
{
	private int _roomId;
	private int _maxMembers;
	private int _minLevel;
	private int _maxLevel;
	private int _lootType;
	private String _roomTitle;

	@Override
	protected void readImpl()
	{
		this._roomId = this.readInt();
		this._maxMembers = this.readInt();
		this._minLevel = this.readInt();
		this._maxLevel = this.readInt();
		this._lootType = this.readInt();
		this._roomTitle = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._roomId <= 0 && player.getMatchingRoom() == null)
			{
				PartyMatchingRoom room = new PartyMatchingRoom(this._roomTitle, this._lootType, this._minLevel, this._maxLevel, this._maxMembers, player);
				player.setMatchingRoom(room);
			}
			else
			{
				MatchingRoom room = player.getMatchingRoom();
				if (room.getId() == this._roomId && room.getRoomType() == MatchingRoomType.PARTY && room.isLeader(player))
				{
					room.setLootType(this._lootType);
					room.setMinLevel(this._minLevel);
					room.setMaxLevel(this._maxLevel);
					room.setMaxMembers(this._maxMembers);
					room.setTitle(this._roomTitle);
					PartyRoomInfo packet = new PartyRoomInfo((PartyMatchingRoom) room);

					for (Player member : room.getMembers())
					{
						member.sendPacket(packet);
					}
				}
			}
		}
	}
}
