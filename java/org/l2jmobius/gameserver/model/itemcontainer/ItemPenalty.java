package org.l2jmobius.gameserver.model.itemcontainer;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemLocation;

public class ItemPenalty extends ItemContainer
{
	private final Player _player;

	public ItemPenalty(Player player)
	{
		this._player = player;
		this.restore();
	}

	@Override
	public Player getOwner()
	{
		return this._player;
	}

	@Override
	protected ItemLocation getBaseLocation()
	{
		return ItemLocation.PENALTY;
	}
}
