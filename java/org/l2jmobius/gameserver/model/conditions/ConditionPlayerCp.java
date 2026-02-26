package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerCp extends Condition
{
	private final int _cp;

	public ConditionPlayerCp(int cp)
	{
		this._cp = cp;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		return effector != null && effector.getCurrentCp() * 100.0 / effector.getMaxCp() >= this._cp;
	}
}
