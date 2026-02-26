package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.AttendanceRewardsConfig;
import org.l2jmobius.gameserver.config.ServerConfig;
import org.l2jmobius.gameserver.config.WorldExchangeConfig;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExBRVersion extends ServerPacket
{
	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		if (WorldExchangeConfig.ENABLE_WORLD_EXCHANGE)
		{
			ServerPackets.EX_BR_VERSION.writeId(this, buffer);
			buffer.writeByte(0);
			buffer.writeByte(AttendanceRewardsConfig.ENABLE_ATTENDANCE_REWARDS);
			buffer.writeInt(ServerConfig.SERVER_ID);
			buffer.writeByte(0);
		}
	}
}
