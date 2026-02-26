package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionItemId extends Condition
{
	private final int _itemId;

	public ConditionItemId(int itemId)
	{
		this._itemId = itemId;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		return item != null && item.getId() == this._itemId;
	}
}
