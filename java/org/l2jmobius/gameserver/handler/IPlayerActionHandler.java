package org.l2jmobius.gameserver.handler;

import org.l2jmobius.gameserver.model.ActionDataHolder;
import org.l2jmobius.gameserver.model.actor.Player;

public interface IPlayerActionHandler
{
	void onAction(Player var1, ActionDataHolder var2, boolean var3, boolean var4);

	default boolean isPetAction()
	{
		return false;
	}
}
