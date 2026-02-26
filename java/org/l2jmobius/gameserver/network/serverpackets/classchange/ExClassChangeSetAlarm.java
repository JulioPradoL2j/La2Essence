package org.l2jmobius.gameserver.network.serverpackets.classchange;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExClassChangeSetAlarm extends ServerPacket
{
	public static final ExClassChangeSetAlarm STATIC_PACKET = new ExClassChangeSetAlarm();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CLASS_CHANGE_SET_ALARM.writeId(this, buffer);
	}
}
