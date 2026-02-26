package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerInvSize extends Condition
{
	private final int _size;

	public ConditionPlayerInvSize(int size)
	{
		this._size = size;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		Player player = effector.asPlayer();
		return player != null ? player.getInventory().getNonQuestSize() <= player.getInventoryLimit() - this._size : true;
	}
}
