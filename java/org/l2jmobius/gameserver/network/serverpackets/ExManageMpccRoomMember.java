package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.MapRegionManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.matching.CommandChannelMatchingRoom;
import org.l2jmobius.gameserver.model.groups.matching.MatchingMemberType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.enums.ExManagePartyRoomMemberType;

public class ExManageMpccRoomMember extends ServerPacket
{
	private final Player _player;
	private final MatchingMemberType _memberType;
	private final ExManagePartyRoomMemberType _type;

	public ExManageMpccRoomMember(Player player, CommandChannelMatchingRoom room, ExManagePartyRoomMemberType mode)
	{
		this._player = player;
		this._memberType = room.getMemberType(player);
		this._type = mode;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MANAGE_PARTY_ROOM_MEMBER.writeId(this, buffer);
		buffer.writeInt(this._type.ordinal());
		buffer.writeInt(this._player.getObjectId());
		buffer.writeString(this._player.getName());
		buffer.writeInt(this._player.getPlayerClass().getId());
		buffer.writeInt(this._player.getLevel());
		buffer.writeInt(MapRegionManager.getInstance().getBBs(this._player.getLocation()));
		buffer.writeInt(this._memberType.ordinal());
	}
}
