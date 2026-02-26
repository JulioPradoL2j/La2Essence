package org.l2jmobius.gameserver.model.cubic.conditions;

import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.cubic.Cubic;

public class RangeCondition implements ICubicCondition
{
	private final int _range;

	public RangeCondition(int range)
	{
		this._range = range;
	}

	@Override
	public boolean test(Cubic cubic, Creature owner, WorldObject target)
	{
		return owner.calculateDistance2D(target) <= this._range;
	}
}
