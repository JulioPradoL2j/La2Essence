package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.groups.matching.CommandChannelMatchingRoom;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExMPCCRoomInfo extends ServerPacket
{
	private final CommandChannelMatchingRoom _room;

	public ExMPCCRoomInfo(CommandChannelMatchingRoom room)
	{
		this._room = room;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MPCC_ROOM_INFO.writeId(this, buffer);
		buffer.writeInt(this._room.getId());
		buffer.writeInt(this._room.getMaxMembers());
		buffer.writeInt(this._room.getMinLevel());
		buffer.writeInt(this._room.getMaxLevel());
		buffer.writeInt(this._room.getLootType());
		buffer.writeInt(this._room.getLocation());
		buffer.writeString(this._room.getTitle());
	}
}
