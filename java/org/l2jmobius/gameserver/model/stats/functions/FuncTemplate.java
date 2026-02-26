package org.l2jmobius.gameserver.model.stats.functions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.conditions.Condition;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.stats.Stat;

public class FuncTemplate
{
	private final Class<?> _functionClass;
	private final Condition _attachCond;
	private final Condition _applyCond;
	private final Stat _stat;
	private final int _order;
	private final double _value;

	public FuncTemplate(Condition attachCond, Condition applyCond, String functionName, int order, Stat stat, double value)
	{
		StatFunction function = StatFunction.valueOf(functionName.toUpperCase());
		if (order >= 0)
		{
			this._order = order;
		}
		else
		{
			this._order = function.getOrder();
		}

		this._attachCond = attachCond;
		this._applyCond = applyCond;
		this._stat = stat;
		this._value = value;

		try
		{
			this._functionClass = Class.forName("org.l2jmobius.gameserver.model.stats.functions.Func" + function.getName());
		}
		catch (ClassNotFoundException var10)
		{
			throw new RuntimeException(var10);
		}
	}

	public Class<?> getFunctionClass()
	{
		return this._functionClass;
	}

	public Stat getStat()
	{
		return this._stat;
	}

	public int getOrder()
	{
		return this._order;
	}

	public double getValue()
	{
		return this._value;
	}

	public boolean meetCondition(Creature effected, Skill skill)
	{
		return this._attachCond != null && !this._attachCond.test(effected, effected, skill) ? false : this._applyCond == null || this._applyCond.test(effected, effected, skill);
	}
}
