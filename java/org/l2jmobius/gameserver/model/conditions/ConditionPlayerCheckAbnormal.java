package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.AbnormalType;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerCheckAbnormal extends Condition
{
	private final AbnormalType _type;
	private final int _level;

	public ConditionPlayerCheckAbnormal(AbnormalType type)
	{
		this._type = type;
		this._level = -1;
	}

	public ConditionPlayerCheckAbnormal(AbnormalType type, int level)
	{
		this._type = type;
		this._level = level;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		return this._level == -1 ? effector.getEffectList().hasAbnormalType(this._type) : effector.getEffectList().hasAbnormalType(this._type, info -> this._level >= info.getSkill().getAbnormalLevel());
	}
}
