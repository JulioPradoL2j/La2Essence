package org.l2jmobius.gameserver.model.variables;

import org.l2jmobius.gameserver.model.StatSet;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.Summon;

public class NpcVariables extends StatSet
{
	@Override
	public int getInt(String key)
	{
		return super.getInt(key, 0);
	}

	public boolean hasVariable(String name)
	{
		return this.getSet().containsKey(name);
	}

	public Player getPlayer(String name)
	{
		return this.getObject(name, Player.class);
	}

	public Summon getSummon(String name)
	{
		return this.getObject(name, Summon.class);
	}
}
