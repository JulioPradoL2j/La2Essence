package org.l2jmobius.gameserver.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.l2jmobius.gameserver.model.actor.holders.player.DailyMissionDataHolder;
import org.l2jmobius.gameserver.scripting.ScriptEngine;

public class DailyMissionHandler
{
	private final Map<String, Function<DailyMissionDataHolder, AbstractDailyMissionHandler>> _handlerFactories = new HashMap<>();

	public void registerHandler(String name, Function<DailyMissionDataHolder, AbstractDailyMissionHandler> handlerFactory)
	{
		this._handlerFactories.put(name, handlerFactory);
	}

	public Function<DailyMissionDataHolder, AbstractDailyMissionHandler> getHandler(String name)
	{
		return this._handlerFactories.get(name);
	}

	public int size()
	{
		return this._handlerFactories.size();
	}

	public void executeScript()
	{
		try
		{
			ScriptEngine.getInstance().executeScript(ScriptEngine.ONE_DAY_REWARD_MASTER_HANDLER);
		}
		catch (Exception var2)
		{
			throw new Error("Problems while running DailyMissionMasterHandler", var2);
		}
	}

	public static DailyMissionHandler getInstance()
	{
		return DailyMissionHandler.SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final DailyMissionHandler INSTANCE = new DailyMissionHandler();
	}
}
