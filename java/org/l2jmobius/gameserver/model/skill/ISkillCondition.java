package org.l2jmobius.gameserver.model.skill;

import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;

public interface ISkillCondition
{
	boolean canUse(Creature var1, Skill var2, WorldObject var3);
}
