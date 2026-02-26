package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.AbnormalType;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionTargetAbnormalType extends Condition
{
	private final AbnormalType _abnormalType;

	public ConditionTargetAbnormalType(AbnormalType abnormalType)
	{
		this._abnormalType = abnormalType;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		return effected.hasAbnormalType(this._abnormalType);
	}
}
