package org.l2jmobius.gameserver.network.serverpackets.mentoring;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExMentorAdd extends ServerPacket
{
	final Player _mentor;

	public ExMentorAdd(Player mentor)
	{
		this._mentor = mentor;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MENTOR_ADD.writeId(this, buffer);
		buffer.writeString(this._mentor.getName());
		buffer.writeInt(this._mentor.getActiveClass());
		buffer.writeInt(this._mentor.getLevel());
	}
}
