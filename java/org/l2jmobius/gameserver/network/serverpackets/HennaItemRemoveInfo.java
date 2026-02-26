package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.henna.Henna;
import org.l2jmobius.gameserver.model.stats.BaseStat;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class HennaItemRemoveInfo extends ServerPacket
{
	private final Player _player;
	private final Henna _henna;

	public HennaItemRemoveInfo(Henna henna, Player player)
	{
		this._henna = henna;
		this._player = player;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.HENNA_UNEQUIP_INFO.writeId(this, buffer);
		buffer.writeInt(this._henna.getDyeId());
		buffer.writeInt(this._henna.getDyeItemId());
		buffer.writeLong(this._henna.getCancelCount());
		buffer.writeLong(this._henna.getCancelFee());
		buffer.writeInt(this._henna.isAllowedClass(this._player));
		buffer.writeLong(this._player.getAdena());
		buffer.writeInt(this._player.getINT());
		buffer.writeShort(this._player.getINT() - this._henna.getBaseStats(BaseStat.INT));
		buffer.writeInt(this._player.getSTR());
		buffer.writeShort(this._player.getSTR() - this._henna.getBaseStats(BaseStat.STR));
		buffer.writeInt(this._player.getCON());
		buffer.writeShort(this._player.getCON() - this._henna.getBaseStats(BaseStat.CON));
		buffer.writeInt(this._player.getMEN());
		buffer.writeShort(this._player.getMEN() - this._henna.getBaseStats(BaseStat.MEN));
		buffer.writeInt(this._player.getDEX());
		buffer.writeShort(this._player.getDEX() - this._henna.getBaseStats(BaseStat.DEX));
		buffer.writeInt(this._player.getWIT());
		buffer.writeShort(this._player.getWIT() - this._henna.getBaseStats(BaseStat.WIT));
		buffer.writeInt(0);
		buffer.writeShort(0);
		buffer.writeInt(0);
		buffer.writeShort(0);
		buffer.writeInt(this._henna.getDuration() * 60000);
	}
}
