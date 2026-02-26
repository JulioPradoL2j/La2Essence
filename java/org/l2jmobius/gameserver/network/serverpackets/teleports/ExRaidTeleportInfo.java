package org.l2jmobius.gameserver.network.serverpackets.teleports;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRaidTeleportInfo extends ServerPacket
{
	private final boolean _available;

	public ExRaidTeleportInfo(Player player)
	{
		this._available = System.currentTimeMillis() - player.getVariables().getLong("LastFreeRaidTeleportTime", 0L) < 86400000L;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RAID_TELEPORT_INFO.writeId(this, buffer);
		buffer.writeInt(this._available);
	}
}
