package org.l2jmobius.gameserver.handler;

import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.skill.targets.TargetType;

public interface ITargetTypeHandler
{
	WorldObject getTarget(Creature var1, WorldObject var2, Skill var3, boolean var4, boolean var5, boolean var6);

	Enum<TargetType> getTargetType();
}
