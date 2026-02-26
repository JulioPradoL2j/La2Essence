package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerCanSummonServitor extends Condition
{
	private final boolean _value;

	public ConditionPlayerCanSummonServitor(boolean value)
	{
		this._value = value;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		Player player = effector.asPlayer();
		if (player == null)
		{
			return false;
		}
		boolean canSummon = true;
		if (!player.isFlyingMounted() && !player.isMounted() && !player.inObserverMode() && !player.isTeleporting())
		{
			if (player.getServitors().size() >= 4)
			{
				canSummon = false;
			}
		}
		else
		{
			canSummon = false;
		}

		return canSummon == this._value;
	}
}
