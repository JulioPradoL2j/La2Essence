package org.l2jmobius.gameserver.network.serverpackets.huntingzones;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class TimedHuntingZoneExit extends ServerPacket
{
	private final int _zoneId;

	public TimedHuntingZoneExit(int zoneId)
	{
		this._zoneId = zoneId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_TIME_RESTRICT_FIELD_USER_EXIT.writeId(this, buffer);
		buffer.writeInt(this._zoneId);
	}

	@Override
	public void runImpl(Player player)
	{
		player.getVariables().set("LAST_HUNTING_ZONE_ID", 0);
	}
}
