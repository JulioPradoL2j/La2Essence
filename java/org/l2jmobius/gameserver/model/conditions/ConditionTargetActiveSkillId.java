package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionTargetActiveSkillId extends Condition
{
	private final int _skillId;
	private final int _skillLevel;

	public ConditionTargetActiveSkillId(int skillId)
	{
		this._skillId = skillId;
		this._skillLevel = -1;
	}

	public ConditionTargetActiveSkillId(int skillId, int skillLevel)
	{
		this._skillId = skillId;
		this._skillLevel = skillLevel;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		Skill knownSkill = effected.getKnownSkill(this._skillId);
		return knownSkill == null ? false : this._skillLevel == -1 || this._skillLevel <= knownSkill.getLevel();
	}
}
