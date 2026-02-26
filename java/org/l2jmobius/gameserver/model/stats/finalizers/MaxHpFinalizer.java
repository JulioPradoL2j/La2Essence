package org.l2jmobius.gameserver.model.stats.finalizers;

import java.util.OptionalDouble;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.data.xml.EnchantItemHPBonusData;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Pet;
import org.l2jmobius.gameserver.model.item.enums.BodyPart;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.itemcontainer.Inventory;
import org.l2jmobius.gameserver.model.stats.BaseStat;
import org.l2jmobius.gameserver.model.stats.IStatFunction;
import org.l2jmobius.gameserver.model.stats.Stat;

public class MaxHpFinalizer implements IStatFunction
{
	@Override
	public double calc(Creature creature, OptionalDouble base, Stat stat)
	{
		this.throwIfPresent(base);
		double baseValue = creature.getTemplate().getBaseValue(stat, 0.0);
		if (creature.isPet())
		{
			Pet pet = creature.asPet();
			baseValue = pet.getPetLevelData().getPetMaxHP();
		}
		else if (creature.isPlayer())
		{
			Player player = creature.asPlayer();
			if (player != null)
			{
				baseValue = player.getTemplate().getBaseHpMax(player.getLevel());
			}
		}

		double conBonus = creature.getCON() > 0 ? BaseStat.CON.calcBonus(creature) : 1.0;
		baseValue *= conBonus;
		return defaultValue(creature, stat, baseValue);
	}

	private static double defaultValue(Creature creature, Stat stat, double baseValue)
	{
		double mul = creature.getStat().getMul(stat);
		double add = creature.getStat().getAdd(stat);
		double maxHp = mul * baseValue + add + creature.getStat().getMoveTypeValue(stat, creature.getMoveType());
		boolean isPlayer = creature.isPlayer();
		Inventory inv = creature.getInventory();
		if (inv == null)
		{
			if (isPlayer)
			{
				if (creature.asPlayer().isCursedWeaponEquipped())
				{
					return Double.MAX_VALUE;
				}
				mul = creature.getStat().getMul(Stat.HP_LIMIT);
				add = creature.getStat().getAdd(Stat.HP_LIMIT);
				return Math.min(maxHp, PlayerConfig.MAX_HP * mul + add);
			}
			return maxHp;
		}
		for (Item item : inv.getPaperdollItems())
		{
			maxHp += item.getTemplate().getStats(stat, 0.0);
			if (item.isArmor() && item.isEnchanted())
			{
				BodyPart bodyPart = item.getTemplate().getBodyPart();
				if (bodyPart != BodyPart.NECK && bodyPart != BodyPart.LR_EAR && bodyPart != BodyPart.LR_FINGER)
				{
					maxHp += EnchantItemHPBonusData.getInstance().getHPBonus(item);
				}
			}
		}

		double hpLimit;
		if (isPlayer && !creature.asPlayer().isCursedWeaponEquipped())
		{
			mul = creature.getStat().getMul(Stat.HP_LIMIT);
			add = creature.getStat().getAdd(Stat.HP_LIMIT);
			hpLimit = PlayerConfig.MAX_HP * mul + add;
		}
		else
		{
			hpLimit = Double.MAX_VALUE;
		}

		return creature.isPlayer() ? Math.min(Math.min(maxHp, hpLimit), 2.147483647E9) : Math.min(maxHp, hpLimit);
	}
}
