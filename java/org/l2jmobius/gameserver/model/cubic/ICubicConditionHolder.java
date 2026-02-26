package org.l2jmobius.gameserver.model.cubic;

import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.cubic.conditions.ICubicCondition;

public interface ICubicConditionHolder
{
	boolean validateConditions(Cubic var1, Creature var2, WorldObject var3);

	void addCondition(ICubicCondition var1);
}
