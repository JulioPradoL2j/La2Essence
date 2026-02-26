package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;

public interface ICondition
{
	boolean test(Creature var1, WorldObject var2);
}
