package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class NormalCamera extends ServerPacket
{
	public static final NormalCamera STATIC_PACKET = new NormalCamera();

	private NormalCamera()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.NORMAL_CAMERA.writeId(this, buffer);
	}
}
