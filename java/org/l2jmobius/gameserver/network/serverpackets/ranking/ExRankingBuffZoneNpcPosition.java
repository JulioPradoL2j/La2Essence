package org.l2jmobius.gameserver.network.serverpackets.ranking;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.GlobalVariablesManager;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRankingBuffZoneNpcPosition extends ServerPacket
{
	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RANKING_CHAR_BUFFZONE_NPC_POSITION.writeId(this, buffer);
		if (GlobalVariablesManager.getInstance().getLong("RANKING_POWER_COOLDOWN", 0L) > System.currentTimeMillis())
		{
			List<Integer> location = GlobalVariablesManager.getInstance().getIntegerList("RANKING_POWER_LOCATION");
			buffer.writeByte(1);
			buffer.writeInt(location.get(0));
			buffer.writeInt(location.get(1));
			buffer.writeInt(location.get(2));
		}
		else
		{
			buffer.writeByte(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
		}
	}
}
