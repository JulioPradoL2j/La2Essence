package org.l2jmobius.gameserver.model.stats.finalizers;

import java.util.OptionalDouble;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.stats.IStatFunction;
import org.l2jmobius.gameserver.model.stats.Stat;

public class MpVampiricChanceFinalizer implements IStatFunction
{
	@Override
	public double calc(Creature creature, OptionalDouble base, Stat stat)
	{
		this.throwIfPresent(base);
		double amount = creature.getStat().getValue(Stat.ABSORB_MANA_DAMAGE_PERCENT, 0.0) * 100.0;
		double mpVampiricSum = creature.getStat().getMpVampiricSum();
		return amount > 0.0 ? Stat.defaultValue(creature, stat, Math.min(1.0, mpVampiricSum / amount / 100.0)) : 0.0;
	}
}
