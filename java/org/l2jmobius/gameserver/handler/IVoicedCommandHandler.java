package org.l2jmobius.gameserver.handler;

import org.l2jmobius.gameserver.model.actor.Player;

public interface IVoicedCommandHandler
{
	boolean onCommand(String var1, Player var2, String var3);

	String[] getCommandList();
}
