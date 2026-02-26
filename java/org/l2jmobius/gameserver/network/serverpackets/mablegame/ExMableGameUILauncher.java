package org.l2jmobius.gameserver.network.serverpackets.mablegame;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExMableGameUILauncher extends ServerPacket
{
	public static final ExMableGameUILauncher STATIC_PACKET = new ExMableGameUILauncher();

	private ExMableGameUILauncher()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MABLE_GAME_UI_LAUNCHER.writeId(this, buffer);
		buffer.writeByte(1);
	}
}
