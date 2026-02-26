package org.l2jmobius.gameserver.model.actor.stat;

import org.l2jmobius.gameserver.model.actor.instance.StaticObject;

public class StaticObjectStat extends CreatureStat
{
	public StaticObjectStat(StaticObject activeChar)
	{
		super(activeChar);
	}

	@Override
	public StaticObject getActiveChar()
	{
		return (StaticObject) super.getActiveChar();
	}

	@Override
	public int getLevel()
	{
		return this.getActiveChar().getLevel();
	}
}
