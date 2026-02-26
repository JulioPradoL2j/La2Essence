package org.l2jmobius.gameserver.model.itemcontainer;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemLocation;

public class PlayerWarehouse extends Warehouse
{
	private final Player _owner;

	public PlayerWarehouse(Player owner)
	{
		this._owner = owner;
	}

	@Override
	public String getName()
	{
		return "Warehouse";
	}

	@Override
	public Player getOwner()
	{
		return this._owner;
	}

	@Override
	public ItemLocation getBaseLocation()
	{
		return ItemLocation.WAREHOUSE;
	}

	@Override
	public boolean validateCapacity(long slots)
	{
		return this._items.size() + slots <= this._owner.getWareHouseLimit();
	}
}
