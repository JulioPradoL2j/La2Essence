package org.l2jmobius.gameserver.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.l2jmobius.gameserver.model.StatSet;
import org.l2jmobius.gameserver.model.skill.ISkillCondition;
import org.l2jmobius.gameserver.scripting.ScriptEngine;

public class SkillConditionHandler
{
	private final Map<String, Function<StatSet, ISkillCondition>> _skillConditionHandlerFactories = new HashMap<>();

	public void registerHandler(String name, Function<StatSet, ISkillCondition> handlerFactory)
	{
		this._skillConditionHandlerFactories.put(name, handlerFactory);
	}

	public Function<StatSet, ISkillCondition> getHandlerFactory(String name)
	{
		return this._skillConditionHandlerFactories.get(name);
	}

	public int size()
	{
		return this._skillConditionHandlerFactories.size();
	}

	public void executeScript()
	{
		try
		{
			ScriptEngine.getInstance().executeScript(ScriptEngine.SKILL_CONDITION_HANDLER_FILE);
		}
		catch (Exception var2)
		{
			throw new Error("Problems while running SkillMasterHandler", var2);
		}
	}

	public static SkillConditionHandler getInstance()
	{
		return SkillConditionHandler.SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final SkillConditionHandler INSTANCE = new SkillConditionHandler();
	}
}
