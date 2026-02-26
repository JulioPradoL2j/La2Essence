package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerActiveSkillId extends Condition
{
	private final int _skillId;
	private final int _skillLevel;

	public ConditionPlayerActiveSkillId(int skillId)
	{
		this._skillId = skillId;
		this._skillLevel = -1;
	}

	public ConditionPlayerActiveSkillId(int skillId, int skillLevel)
	{
		this._skillId = skillId;
		this._skillLevel = skillLevel;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		Skill knownSkill = effector.getKnownSkill(this._skillId);
		return knownSkill == null ? false : this._skillLevel == -1 || this._skillLevel <= knownSkill.getLevel();
	}
}
