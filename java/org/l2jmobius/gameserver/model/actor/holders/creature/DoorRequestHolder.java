package org.l2jmobius.gameserver.model.actor.holders.creature;

import org.l2jmobius.gameserver.model.actor.instance.Door;

public class DoorRequestHolder
{
	private final Door _target;

	public DoorRequestHolder(Door door)
	{
		this._target = door;
	}

	public Door getDoor()
	{
		return this._target;
	}
}
