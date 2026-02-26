package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExBirthdayPopup extends ServerPacket
{
	public static final ExBirthdayPopup STATIC_PACKET = new ExBirthdayPopup();

	private ExBirthdayPopup()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_NOTIFY_BIRTHDAY.writeId(this, buffer);
	}
}
