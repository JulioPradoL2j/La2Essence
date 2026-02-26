package org.l2jmobius.gameserver.model.actor.status;

import org.l2jmobius.gameserver.model.actor.instance.Door;

public class DoorStatus extends CreatureStatus
{
	public DoorStatus(Door activeChar)
	{
		super(activeChar);
	}

	@Override
	public Door getActiveChar()
	{
		return super.getActiveChar().asDoor();
	}
}
