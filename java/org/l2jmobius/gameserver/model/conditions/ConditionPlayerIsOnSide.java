package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.siege.CastleSide;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerIsOnSide extends Condition
{
	private final CastleSide _side;

	public ConditionPlayerIsOnSide(CastleSide side)
	{
		this._side = side;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		return effector != null && effector.isPlayer() ? effector.asPlayer().getPlayerSide() == this._side : false;
	}
}
