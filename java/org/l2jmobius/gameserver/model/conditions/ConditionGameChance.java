package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionGameChance extends Condition
{
	private final int _chance;

	public ConditionGameChance(int chance)
	{
		this._chance = chance;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		return Rnd.get(100) < this._chance;
	}
}
