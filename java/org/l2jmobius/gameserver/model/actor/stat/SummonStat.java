package org.l2jmobius.gameserver.model.actor.stat;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.model.actor.Summon;

public class SummonStat extends PlayableStat
{
	public SummonStat(Summon activeChar)
	{
		super(activeChar);
	}

	@Override
	public Summon getActiveChar()
	{
		return super.getActiveChar().asSummon();
	}

	@Override
	public double getRunSpeed()
	{
		double val = super.getRunSpeed() + PlayerConfig.RUN_SPD_BOOST;
		return val > PlayerConfig.MAX_RUN_SPEED_SUMMON ? PlayerConfig.MAX_RUN_SPEED_SUMMON : val;
	}

	@Override
	public double getWalkSpeed()
	{
		double val = super.getWalkSpeed() + PlayerConfig.RUN_SPD_BOOST;
		return val > PlayerConfig.MAX_RUN_SPEED_SUMMON ? PlayerConfig.MAX_RUN_SPEED_SUMMON : val;
	}
}
