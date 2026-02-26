package org.l2jmobius.gameserver.network.serverpackets.huntingzones;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class TimedHuntingZoneEnter extends ServerPacket
{
	private final Player _player;
	private final int _zoneId;

	public TimedHuntingZoneEnter(Player player, int zoneId)
	{
		this._player = player;
		this._zoneId = zoneId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_TIME_RESTRICT_FIELD_USER_ENTER.writeId(this, buffer);
		buffer.writeByte(1);
		buffer.writeInt(this._zoneId);
		buffer.writeInt((int) (System.currentTimeMillis() / 1000L));
		buffer.writeInt(this._player.getTimedHuntingZoneRemainingTime(this._zoneId) / 1000 + 59);
	}
}
