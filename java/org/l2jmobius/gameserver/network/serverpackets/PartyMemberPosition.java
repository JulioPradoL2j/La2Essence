package org.l2jmobius.gameserver.network.serverpackets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.Party;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PartyMemberPosition extends ServerPacket
{
	private final Map<Integer, Location> locations = new HashMap<>();

	public PartyMemberPosition(Party party)
	{
		this.reuse(party);
	}

	public void reuse(Party party)
	{
		this.locations.clear();

		for (Player member : party.getMembers())
		{
			if (member != null)
			{
				this.locations.put(member.getObjectId(), member.getLocation());
			}
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PARTY_MEMBER_POSITION.writeId(this, buffer);
		buffer.writeInt(this.locations.size());

		for (Entry<Integer, Location> entry : this.locations.entrySet())
		{
			Location loc = entry.getValue();
			buffer.writeInt(entry.getKey());
			buffer.writeInt(loc.getX());
			buffer.writeInt(loc.getY());
			buffer.writeInt(loc.getZ());
		}
	}
}
