package org.l2jmobius.gameserver.model.stats.finalizers;

import java.util.OptionalDouble;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.creature.Race;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.stats.IStatFunction;
import org.l2jmobius.gameserver.model.stats.Stat;

public class ShotsBonusFinalizer implements IStatFunction
{
	@Override
	public double calc(Creature creature, OptionalDouble base, Stat stat)
	{
		this.throwIfPresent(base);
		double baseValue = 1.0;
		Player player = creature.asPlayer();
		if (player != null)
		{
			Item weapon = player.getActiveWeaponInstance();
			if (weapon != null && weapon.isEnchanted())
			{
				switch (weapon.getWeaponItem().getItemGrade())
				{
					case D:
					case C:
						baseValue += weapon.getEnchantLevel() * 0.4 / 100.0;
						break;
					case B:
						baseValue += weapon.getEnchantLevel() * 0.7 / 100.0;
						break;
					case A:
						baseValue += weapon.getEnchantLevel() * 1.4 / 100.0;
						break;
					case S:
						baseValue += weapon.getEnchantLevel() * 1.6 / 100.0;
				}
			}

			if (this.isMageCaster(player))
			{
				if (player.getActiveShappireJewel() != null)
				{
					baseValue += player.getActiveShappireJewel().getBonus();
				}
			}
			else if (player.getActiveRubyJewel() != null)
			{
				baseValue += player.getActiveRubyJewel().getBonus();
			}
		}

		return Stat.defaultValue(creature, stat, baseValue);
	}

	protected boolean isMageCaster(Player player)
	{
		return player.isMageClass() && player.getRace() != Race.ORC;
	}
}
