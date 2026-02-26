package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Set;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExShowContactList extends ServerPacket
{
	private final Set<String> _contacts;

	public ExShowContactList(Player player)
	{
		this._contacts = player.getContactList().getAllContacts();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_POST_FRIEND.writeId(this, buffer);
		buffer.writeInt(this._contacts.size());
		this._contacts.forEach(buffer::writeString);
	}
}
