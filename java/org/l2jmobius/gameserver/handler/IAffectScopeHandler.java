package org.l2jmobius.gameserver.handler;

import java.util.function.Consumer;

import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.skill.targets.AffectScope;

public interface IAffectScopeHandler
{
	void forEachAffected(Creature var1, WorldObject var2, Skill var3, Consumer<? super WorldObject> var4);

	Enum<AffectScope> getAffectScopeType();
}
