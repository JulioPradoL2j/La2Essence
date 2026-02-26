package org.l2jmobius.gameserver.network.serverpackets.huntingzones;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class TimeRestrictFieldUserAlarm extends ServerPacket
{
	private final Player _player;
	private final int _zoneId;

	public TimeRestrictFieldUserAlarm(Player player, int zoneId)
	{
		this._player = player;
		this._zoneId = zoneId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_TIME_RESTRICT_FIELD_USER_ALARM.writeId(this, buffer);
		buffer.writeInt(this._zoneId);
		buffer.writeInt(this._player.getTimedHuntingZoneRemainingTime(this._zoneId) / 1000 + 59);
	}
}
