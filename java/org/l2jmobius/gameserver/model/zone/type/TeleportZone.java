package org.l2jmobius.gameserver.model.zone.type;

import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.enums.creature.InstanceType;
import org.l2jmobius.gameserver.model.zone.ZoneType;

public class TeleportZone extends ZoneType
{
	private int _x = -1;
	private int _y = -1;
	private int _z = -1;

	public TeleportZone(int id)
	{
		super(id);
		this.setTargetType(InstanceType.Player);
	}

	@Override
	public void setParameter(String name, String value)
	{
		switch (name)
		{
			case "oustX":
				this._x = Integer.parseInt(value);
				break;
			case "oustY":
				this._y = Integer.parseInt(value);
				break;
			case "oustZ":
				this._z = Integer.parseInt(value);
				break;
			default:
				super.setParameter(name, value);
		}
	}

	@Override
	protected void onEnter(Creature creature)
	{
		creature.teleToLocation(new Location(this._x, this._y, this._z));
	}

	@Override
	protected void onExit(Creature creature)
	{
	}
}
