package org.l2jmobius.gameserver.handler;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.skill.targets.AffectObject;

public interface IAffectObjectHandler
{
	boolean checkAffectedObject(Creature var1, Creature var2);

	Enum<AffectObject> getAffectObjectType();
}
