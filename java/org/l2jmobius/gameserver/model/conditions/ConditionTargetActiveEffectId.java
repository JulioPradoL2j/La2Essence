package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.BuffInfo;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionTargetActiveEffectId extends Condition
{
	private final int _effectId;
	private final int _effectLvl;

	public ConditionTargetActiveEffectId(int effectId)
	{
		this._effectId = effectId;
		this._effectLvl = -1;
	}

	public ConditionTargetActiveEffectId(int effectId, int effectLevel)
	{
		this._effectId = effectId;
		this._effectLvl = effectLevel;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		BuffInfo info = effected.getEffectList().getBuffInfoBySkillId(this._effectId);
		return info != null && (this._effectLvl == -1 || this._effectLvl <= info.getSkill().getLevel());
	}
}
