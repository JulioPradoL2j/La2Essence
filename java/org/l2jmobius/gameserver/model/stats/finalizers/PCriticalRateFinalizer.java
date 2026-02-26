package org.l2jmobius.gameserver.model.stats.finalizers;

import java.util.OptionalDouble;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.enums.BodyPart;
import org.l2jmobius.gameserver.model.stats.BaseStat;
import org.l2jmobius.gameserver.model.stats.IStatFunction;
import org.l2jmobius.gameserver.model.stats.Stat;

public class PCriticalRateFinalizer implements IStatFunction
{
	@Override
	public double calc(Creature creature, OptionalDouble base, Stat stat)
	{
		this.throwIfPresent(base);
		double baseValue = this.calcWeaponBaseValue(creature, stat);
		if (creature.isPlayer())
		{
			baseValue += this.calcEnchantBodyPart(creature, BodyPart.LEGS);
		}

		double dexBonus = creature.getDEX() > 0 ? BaseStat.DEX.calcBonus(creature) : 1.0;
		double maxPhysicalCritRate;
		if (creature.isPlayable())
		{
			maxPhysicalCritRate = PlayerConfig.MAX_PCRIT_RATE + creature.getStat().getValue(Stat.ADD_MAX_PHYSICAL_CRITICAL_RATE, 0.0);
		}
		else
		{
			maxPhysicalCritRate = Double.MAX_VALUE;
		}

		return this.validateValue(creature, Stat.defaultValue(creature, stat, baseValue * dexBonus * 10.0), 0.0, maxPhysicalCritRate);
	}

	@Override
	public double calcEnchantBodyPartBonus(int enchantLevel, boolean isBlessed)
	{
		return isBlessed ? 0.5 * Math.max(enchantLevel - 3, 0) + 0.5 * Math.max(enchantLevel - 6, 0) : 0.34 * Math.max(enchantLevel - 3, 0) + 0.34 * Math.max(enchantLevel - 6, 0);
	}
}
