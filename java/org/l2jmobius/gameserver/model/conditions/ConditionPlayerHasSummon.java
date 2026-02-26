package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerHasSummon extends Condition
{
	private final boolean _hasSummon;

	public ConditionPlayerHasSummon(boolean hasSummon)
	{
		this._hasSummon = hasSummon;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		Player player = effector.asPlayer();
		return player == null ? false : this._hasSummon == player.hasSummon();
	}
}
