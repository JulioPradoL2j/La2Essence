package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerTransformationId extends Condition
{
	private final int _id;

	public ConditionPlayerTransformationId(int id)
	{
		this._id = id;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		return this._id == -1 ? effector.isTransformed() : effector.getTransformationId() == this._id;
	}
}
