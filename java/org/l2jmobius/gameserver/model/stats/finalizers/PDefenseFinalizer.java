package org.l2jmobius.gameserver.model.stats.finalizers;

import java.util.OptionalDouble;

import org.l2jmobius.gameserver.config.NpcConfig;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Pet;
import org.l2jmobius.gameserver.model.actor.transform.Transform;
import org.l2jmobius.gameserver.model.item.enums.BodyPart;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.itemcontainer.Inventory;
import org.l2jmobius.gameserver.model.stats.IStatFunction;
import org.l2jmobius.gameserver.model.stats.Stat;

public class PDefenseFinalizer implements IStatFunction
{
	private static final int[] SLOTS = new int[]
	{
		6,
		11,
		1,
		12,
		10,
		0,
		28,
		2
	};

	@Override
	public double calc(Creature creature, OptionalDouble base, Stat stat)
	{
		this.throwIfPresent(base);
		double baseValue = creature.getTemplate().getBaseValue(stat, 0.0);
		if (creature.isPet())
		{
			Pet pet = creature.asPet();
			baseValue = pet.getPetLevelData().getPetPDef();
		}

		baseValue += this.calcEnchantedItemBonus(creature, stat);
		Inventory inv = creature.getInventory();
		if (inv != null)
		{
			for (Item item : inv.getPaperdollItems())
			{
				baseValue += item.getTemplate().getStats(stat, 0.0);
			}

			if (creature.isPlayer())
			{
				double armorPhysicalDefence = creature.getStat().getValue(Stat.ARMOR_PHYSICAL_DEFENCE, 0.0) / 5.0;
				Player player = creature.asPlayer();
				Transform transform = creature.getTransformation();

				for (int slot : SLOTS)
				{
					if (!inv.isPaperdollSlotEmpty(slot) || slot == 11 && !inv.isPaperdollSlotEmpty(6) && inv.getPaperdollItem(6).getTemplate().getBodyPart() == BodyPart.FULL_ARMOR)
					{
						baseValue -= transform == null ? player.getTemplate().getBaseDefBySlot(slot) : transform.getBaseDefBySlot(player, slot);
						if (armorPhysicalDefence > 0.0)
						{
							baseValue += armorPhysicalDefence;
						}
					}
				}
			}
		}

		if (creature.isRaid())
		{
			baseValue *= NpcConfig.RAID_PDEFENCE_MULTIPLIER;
		}

		if (creature.getLevel() > 0)
		{
			baseValue *= creature.getLevelMod();
		}

		return this.defaultValue(creature, stat, baseValue);
	}

	protected double defaultValue(Creature creature, Stat stat, double baseValue)
	{
		double mul = Math.max(creature.getStat().getMul(stat), 0.5);
		double add = creature.getStat().getAdd(stat);
		return Math.max(baseValue * mul + add + creature.getStat().getMoveTypeValue(stat, creature.getMoveType()), creature.getTemplate().getBaseValue(stat, 0.0) * 0.2);
	}
}
