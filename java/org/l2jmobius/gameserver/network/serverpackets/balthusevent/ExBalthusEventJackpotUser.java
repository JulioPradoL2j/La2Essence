package org.l2jmobius.gameserver.network.serverpackets.balthusevent;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExBalthusEventJackpotUser extends ServerPacket
{
	public static final ExBalthusEventJackpotUser STATIC_PACKET = new ExBalthusEventJackpotUser();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BALTHUS_JACKPOT_USER.writeId(this, buffer);
	}
}
