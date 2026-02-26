package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.taskmanagers.GameTimeTaskManager;

public class ConditionGameTime extends Condition
{
	private final boolean _required;

	public ConditionGameTime(boolean required)
	{
		this._required = required;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		return GameTimeTaskManager.getInstance().isNight() == this._required;
	}
}
