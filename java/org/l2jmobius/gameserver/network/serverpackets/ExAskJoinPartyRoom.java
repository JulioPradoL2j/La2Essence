package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExAskJoinPartyRoom extends ServerPacket
{
	private final String _charName;
	private final String _roomName;

	public ExAskJoinPartyRoom(Player player)
	{
		this._charName = player.getName();
		this._roomName = player.getMatchingRoom().getTitle();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ASK_JOIN_PARTY_ROOM.writeId(this, buffer);
		buffer.writeString(this._charName);
		buffer.writeString(this._roomName);
	}
}
