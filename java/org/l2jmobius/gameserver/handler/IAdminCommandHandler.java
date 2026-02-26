package org.l2jmobius.gameserver.handler;

import org.l2jmobius.gameserver.model.actor.Player;

public interface IAdminCommandHandler
{
	boolean onCommand(String var1, Player var2);

	String[] getCommandList();
}
