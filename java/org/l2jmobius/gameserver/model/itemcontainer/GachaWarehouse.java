package org.l2jmobius.gameserver.model.itemcontainer;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemLocation;

public class GachaWarehouse extends Warehouse
{
	public static final int MAXIMUM_TEMPORARY_WAREHOUSE_COUNT = 1100;
	private final Creature _owner;

	public GachaWarehouse(Player player)
	{
		this._owner = player;
	}

	@Override
	protected Creature getOwner()
	{
		return this._owner;
	}

	@Override
	protected ItemLocation getBaseLocation()
	{
		return ItemLocation.GACHA;
	}
}
