package org.l2jmobius.gameserver.model.actor.status;

import org.l2jmobius.gameserver.model.actor.instance.StaticObject;

public class StaticObjectStatus extends CreatureStatus
{
	public StaticObjectStatus(StaticObject activeChar)
	{
		super(activeChar);
	}

	@Override
	public StaticObject getActiveChar()
	{
		return (StaticObject) super.getActiveChar();
	}
}
