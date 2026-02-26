package org.l2jmobius.gameserver.model.stats.finalizers;

import java.util.OptionalDouble;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.stats.IStatFunction;
import org.l2jmobius.gameserver.model.stats.Stat;

public class VampiricChanceFinalizer implements IStatFunction
{
	@Override
	public double calc(Creature creature, OptionalDouble base, Stat stat)
	{
		this.throwIfPresent(base);
		double amount = creature.getStat().getValue(Stat.ABSORB_DAMAGE_PERCENT, 0.0) * 100.0;
		double vampiricSum = creature.getStat().getVampiricSum();
		return amount > 0.0 ? Stat.defaultValue(creature, stat, Math.min(1.0, vampiricSum / amount / 100.0)) : 0.0;
	}
}
