package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionTargetCheckCrtEffect extends Condition
{
	private final boolean _isCrtEffect;

	public ConditionTargetCheckCrtEffect(boolean isCrtEffect)
	{
		this._isCrtEffect = isCrtEffect;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		return effected.isNpc() ? effected.asNpc().getTemplate().canBeCrt() == this._isCrtEffect : true;
	}
}
