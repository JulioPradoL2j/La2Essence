package org.l2jmobius.gameserver.network.serverpackets.ranking;

import java.util.concurrent.TimeUnit;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.GlobalVariablesManager;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRankingBuffZoneNpcInfo extends ServerPacket
{
	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RANKING_CHAR_BUFFZONE_NPC_INFO.writeId(this, buffer);
		long cooldown = GlobalVariablesManager.getInstance().getLong("RANKING_POWER_COOLDOWN", 0L);
		long currentTime = System.currentTimeMillis();
		if (cooldown > currentTime)
		{
			long reuseTime = TimeUnit.MILLISECONDS.toSeconds(cooldown - currentTime);
			buffer.writeInt((int) reuseTime);
		}
		else
		{
			buffer.writeInt(0);
		}
	}
}
