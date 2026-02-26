package org.l2jmobius.gameserver.network.serverpackets.ensoul;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExShowEnsoulExtractionWindow extends ServerPacket
{
	public static final ExShowEnsoulExtractionWindow STATIC_PACKET = new ExShowEnsoulExtractionWindow();

	private ExShowEnsoulExtractionWindow()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_ENSOUL_EXTRACTION_WINDOW.writeId(this, buffer);
	}
}
