package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PartySmallWindowDelete extends ServerPacket
{
	private final Player _member;

	public PartySmallWindowDelete(Player member)
	{
		this._member = member;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PARTY_SMALL_WINDOW_DELETE.writeId(this, buffer);
		buffer.writeInt(this._member.getObjectId());
		buffer.writeString(this._member.getName());
	}
}
