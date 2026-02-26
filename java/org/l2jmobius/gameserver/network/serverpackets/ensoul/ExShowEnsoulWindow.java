package org.l2jmobius.gameserver.network.serverpackets.ensoul;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExShowEnsoulWindow extends ServerPacket
{
	public static final ExShowEnsoulWindow STATIC_PACKET = new ExShowEnsoulWindow();

	private ExShowEnsoulWindow()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_ENSOUL_WINDOW.writeId(this, buffer);
	}
}
