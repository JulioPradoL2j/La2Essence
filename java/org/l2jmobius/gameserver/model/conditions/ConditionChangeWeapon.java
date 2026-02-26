package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.item.Weapon;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionChangeWeapon extends Condition
{
	private final boolean _required;

	public ConditionChangeWeapon(boolean required)
	{
		this._required = required;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		Player player = effector.asPlayer();
		if (player == null)
		{
			return false;
		}
		if (this._required)
		{
			Weapon weaponItem = effector.getActiveWeaponItem();
			if ((weaponItem == null) || (weaponItem.getChangeWeaponId() == 0) || player.hasItemRequest())
			{
				return false;
			}
		}

		return true;
	}
}
