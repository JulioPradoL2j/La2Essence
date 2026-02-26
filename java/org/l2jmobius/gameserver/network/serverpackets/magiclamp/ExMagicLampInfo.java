package org.l2jmobius.gameserver.network.serverpackets.magiclamp;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.stat.PlayerStat;
import org.l2jmobius.gameserver.model.stats.Stat;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExMagicLampInfo extends ServerPacket
{
	private final Player _player;
	private final int _expPercentage;
	private final int _count;
	private final int _bonus;

	public ExMagicLampInfo(Player player)
	{
		this._player = player;
		this._expPercentage = this._player.getLampExp() / 10;
		PlayerStat stat = this._player.getStat();
		this._count = (int) stat.getValue(Stat.LAMP_BONUS_EXP, 0.0);
		this._bonus = (int) stat.getValue(Stat.LAMP_BONUS_BUFFS_COUNT, 0.0);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MAGICLAMP_INFO.writeId(this, buffer);
		buffer.writeInt(this._expPercentage);
		buffer.writeInt(this._bonus);
		buffer.writeInt(this._count);
	}
}
