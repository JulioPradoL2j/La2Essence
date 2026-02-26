package org.l2jmobius.gameserver.model.stats.functions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.conditions.Condition;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.stats.Stat;

public class FuncAdd extends AbstractFunction
{
	public FuncAdd(Stat stat, int order, Object owner, double value, Condition applyCond)
	{
		super(stat, order, owner, value, applyCond);
	}

	@Override
	public double calc(Creature effector, Creature effected, Skill skill, double initVal)
	{
		return this.getApplyCond() != null && !this.getApplyCond().test(effector, effected, skill) ? initVal : initVal + this.getValue();
	}
}
