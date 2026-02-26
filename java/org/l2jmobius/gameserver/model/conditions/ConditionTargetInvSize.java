package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionTargetInvSize extends Condition
{
	private final int _size;

	public ConditionTargetInvSize(int size)
	{
		this._size = size;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		if (effected != null && effected.isPlayer())
		{
			Player target = effected.asPlayer();
			return target.getInventory().getNonQuestSize() <= target.getInventoryLimit() - this._size;
		}
		return false;
	}
}
