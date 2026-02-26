package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.matching.CommandChannelMatchingRoom;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoom;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoomType;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ExMPCCRoomInfo;

public class RequestExManageMpccRoom extends ClientPacket
{
	private int _roomId;
	private int _maxMembers;
	private int _minLevel;
	private int _maxLevel;
	private String _title;

	@Override
	protected void readImpl()
	{
		this._roomId = this.readInt();
		this._maxMembers = this.readInt();
		this._minLevel = this.readInt();
		this._maxLevel = this.readInt();
		this.readInt();
		this._title = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			MatchingRoom room = player.getMatchingRoom();
			if (room != null && room.getId() == this._roomId && room.getRoomType() == MatchingRoomType.COMMAND_CHANNEL && room.getLeader() == player)
			{
				room.setTitle(this._title);
				room.setMaxMembers(this._maxMembers);
				room.setMinLevel(this._minLevel);
				room.setMaxLevel(this._maxLevel);
				room.getMembers().forEach(p -> p.sendPacket(new ExMPCCRoomInfo((CommandChannelMatchingRoom) room)));
				player.sendPacket(SystemMessageId.THE_COMMAND_CHANNEL_MATCHING_ROOM_INFORMATION_WAS_EDITED);
			}
		}
	}
}
