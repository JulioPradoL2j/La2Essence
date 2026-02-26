package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.InstanceManager;
import org.l2jmobius.gameserver.managers.MapRegionManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.matching.MatchingMemberType;
import org.l2jmobius.gameserver.model.groups.matching.PartyMatchingRoom;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPartyRoomMember extends ServerPacket
{
	private final PartyMatchingRoom _room;
	private final MatchingMemberType _type;

	public ExPartyRoomMember(Player player, PartyMatchingRoom room)
	{
		this._room = room;
		this._type = room.getMemberType(player);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PARTY_ROOM_MEMBER.writeId(this, buffer);
		buffer.writeInt(this._type.ordinal());
		buffer.writeInt(this._room.getMembersCount());

		for (Player member : this._room.getMembers())
		{
			buffer.writeInt(member.getObjectId());
			buffer.writeString(member.getName());
			buffer.writeInt(member.getActiveClass());
			buffer.writeInt(member.getLevel());
			buffer.writeInt(MapRegionManager.getInstance().getBBs(member.getLocation()));
			buffer.writeInt(this._room.getMemberType(member).ordinal());
			Map<Integer, Long> instanceTimes = InstanceManager.getInstance().getAllInstanceTimes(member);
			buffer.writeInt(instanceTimes.size());

			for (Entry<Integer, Long> entry : instanceTimes.entrySet())
			{
				long instanceTime = TimeUnit.MILLISECONDS.toSeconds(entry.getValue() - System.currentTimeMillis());
				buffer.writeInt(entry.getKey());
				buffer.writeInt((int) instanceTime);
			}
		}
	}
}
