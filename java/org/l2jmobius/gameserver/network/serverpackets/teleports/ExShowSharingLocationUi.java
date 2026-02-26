package org.l2jmobius.gameserver.network.serverpackets.teleports;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExShowSharingLocationUi extends ServerPacket
{
	public static final ExShowSharingLocationUi STATIC_PACKET = new ExShowSharingLocationUi();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHARED_POSITION_SHARING_UI.writeId(this, buffer);
		buffer.writeLong(GeneralConfig.SHARING_LOCATION_COST);
	}
}
