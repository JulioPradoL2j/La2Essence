package org.l2jmobius.gameserver.network.serverpackets.teleports;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExShowTeleportUi extends ServerPacket
{
	public static final ExShowTeleportUi STATIC_PACKET = new ExShowTeleportUi();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_TELEPORT_UI.writeId(this, buffer);
	}
}
