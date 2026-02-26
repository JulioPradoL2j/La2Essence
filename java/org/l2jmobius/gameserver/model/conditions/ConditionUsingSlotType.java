package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.item.Weapon;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionUsingSlotType extends Condition
{
	private final int _mask;

	public ConditionUsingSlotType(int mask)
	{
		this._mask = mask;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		if (effector != null && effector.isPlayer())
		{
			Weapon activeWeapon = effector.getActiveWeaponItem();
			return activeWeapon == null ? false : (activeWeapon.getBodyPart().getMask() & this._mask) != 0L;
		}
		return false;
	}
}
