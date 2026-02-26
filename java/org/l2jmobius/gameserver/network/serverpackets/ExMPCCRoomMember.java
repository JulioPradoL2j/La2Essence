package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.MapRegionManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.matching.CommandChannelMatchingRoom;
import org.l2jmobius.gameserver.model.groups.matching.MatchingMemberType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExMPCCRoomMember extends ServerPacket
{
	private final CommandChannelMatchingRoom _room;
	private final MatchingMemberType _type;

	public ExMPCCRoomMember(Player player, CommandChannelMatchingRoom room)
	{
		this._room = room;
		this._type = room.getMemberType(player);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MPCC_ROOM_MEMBER.writeId(this, buffer);
		buffer.writeInt(this._type.ordinal());
		buffer.writeInt(this._room.getMembersCount());

		for (Player member : this._room.getMembers())
		{
			buffer.writeInt(member.getObjectId());
			buffer.writeString(member.getName());
			buffer.writeInt(member.getLevel());
			buffer.writeInt(member.getPlayerClass().getId());
			buffer.writeInt(MapRegionManager.getInstance().getBBs(member.getLocation()));
			buffer.writeInt(this._room.getMemberType(member).ordinal());
		}
	}
}
