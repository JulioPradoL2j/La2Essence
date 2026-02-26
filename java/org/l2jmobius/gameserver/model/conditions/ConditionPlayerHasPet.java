package org.l2jmobius.gameserver.model.conditions;

import java.util.List;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.Summon;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerHasPet extends Condition
{
	private final List<Integer> _controlItemIds;

	public ConditionPlayerHasPet(List<Integer> itemIds)
	{
		if (itemIds.size() == 1 && itemIds.get(0) == 0)
		{
			this._controlItemIds = null;
		}
		else
		{
			this._controlItemIds = itemIds;
		}
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		if (this._controlItemIds == null)
		{
			return true;
		}
		Player player = effector.asPlayer();
		if (player == null)
		{
			return false;
		}
		Summon pet = player.getPet();
		if (pet == null)
		{
			return false;
		}
		Item controlItem = pet.asPet().getControlItem();
		return controlItem != null && this._controlItemIds.contains(controlItem.getId());
	}
}
