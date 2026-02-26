package org.l2jmobius.gameserver.model.cubic.conditions;

import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.cubic.Cubic;

public interface ICubicCondition
{
	boolean test(Cubic var1, Creature var2, WorldObject var3);
}
