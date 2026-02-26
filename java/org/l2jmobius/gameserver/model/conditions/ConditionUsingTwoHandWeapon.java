package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.item.enums.BodyPart;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionUsingTwoHandWeapon extends Condition
{
	private final boolean _value;

	public ConditionUsingTwoHandWeapon(boolean value)
	{
		this._value = value;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		if (effected != null && effected.isPlayer())
		{
			ItemTemplate weapon = effected.getActiveWeaponItem();
			if (weapon == null)
			{
				return false;
			}
			return this._value ? weapon.getBodyPart() == BodyPart.LR_HAND : weapon.getBodyPart() != BodyPart.LR_HAND;
		}
		return false;
	}
}
