package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.BonusExpType;
import org.l2jmobius.gameserver.model.actor.stat.PlayerStat;
import org.l2jmobius.gameserver.model.stats.Stat;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExUserBoostStat extends ServerPacket
{
	private final Player _player;
	private final BonusExpType _type;

	public ExUserBoostStat(Player player, BonusExpType type)
	{
		this._player = player;
		this._type = type;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_USER_BOOST_STAT.writeId(this, buffer);
		int count = 0;
		int bonus = 0;
		PlayerStat stat = this._player.getStat();
		switch (this._type)
		{
			case VITALITY:
				int vitalityBonus = (int) (stat.getVitalityExpBonus() * 100.0);
				if (vitalityBonus > 0)
				{
					count = 1;
					bonus = vitalityBonus;
				}
				break;
			case BUFFS:
				count = (int) stat.getValue(Stat.BONUS_EXP_BUFFS, 0.0);
				bonus = (int) (stat.getValue(Stat.ACTIVE_BONUS_EXP, 0.0) * 10.0);
				break;
			case PASSIVE:
				count = (int) stat.getValue(Stat.BONUS_EXP_PASSIVES, 0.0);
				bonus = (int) ((stat.getValue(Stat.BONUS_EXP, 0.0) - stat.getValue(Stat.ACTIVE_BONUS_EXP, 0.0)) * 10.0);
		}

		buffer.writeByte(this._type.getId());
		buffer.writeByte(count);
		buffer.writeShort(bonus);
	}
}
