package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionTargetAggro extends Condition
{
	private final boolean _isAggro;

	public ConditionTargetAggro(boolean isAggro)
	{
		this._isAggro = isAggro;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		if (effected != null)
		{
			if (effected.isMonster())
			{
				return effected.asMonster().isAggressive() == this._isAggro;
			}

			if (effected.isPlayer())
			{
				return effected.asPlayer().getReputation() < 0;
			}
		}

		return false;
	}
}
