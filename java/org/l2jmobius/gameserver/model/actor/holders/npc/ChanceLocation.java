package org.l2jmobius.gameserver.model.actor.holders.npc;

import org.l2jmobius.gameserver.model.Location;

public class ChanceLocation extends Location
{
	private final double _chance;

	public ChanceLocation(int x, int y, int z, int heading, double chance)
	{
		super(x, y, z, heading);
		this._chance = chance;
	}

	public double getChance()
	{
		return this._chance;
	}
}
