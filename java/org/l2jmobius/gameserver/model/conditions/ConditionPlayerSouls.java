package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.skill.enums.SoulType;

public class ConditionPlayerSouls extends Condition
{
	private final int _souls;
	private final SoulType _type;

	public ConditionPlayerSouls(int souls, SoulType type)
	{
		this._souls = souls;
		this._type = type;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		return effector.isPlayer() && effector.asPlayer().getChargedSouls(this._type) >= this._souls;
	}
}
