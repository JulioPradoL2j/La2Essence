package org.l2jmobius.gameserver.data.holders;

import org.l2jmobius.gameserver.model.actor.Creature;

public class WarpedSpaceHolder
{
	private final Creature _creature;
	private final int _range;

	public WarpedSpaceHolder(Creature creature, int range)
	{
		this._creature = creature;
		this._range = range;
	}

	public Creature getCreature()
	{
		return this._creature;
	}

	public int getRange()
	{
		return this._range;
	}
}
