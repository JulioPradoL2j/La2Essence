package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.RatesConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExVitalityEffectInfo extends ServerPacket
{
	private final int _vitalityBonus;
	private final int _vitalityItemsRemaining;
	private final int _points;

	public ExVitalityEffectInfo(Player player)
	{
		this._points = player.getVitalityPoints();
		this._vitalityBonus = (int) player.getStat().getVitalityExpBonus() * 100;
		this._vitalityItemsRemaining = RatesConfig.VITALITY_MAX_ITEMS_ALLOWED - player.getVitalityItemsUsed();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_VITALITY_EFFECT_INFO.writeId(this, buffer);
		buffer.writeInt(this._points);
		buffer.writeInt(this._vitalityBonus);
		buffer.writeShort(0);
		buffer.writeShort(this._vitalityItemsRemaining);
		buffer.writeShort(RatesConfig.VITALITY_MAX_ITEMS_ALLOWED);
	}
}
