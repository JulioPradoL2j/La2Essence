package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.item.instance.Item;

public class OnPlayerItemDrop implements IBaseEvent
{
	private final Player _player;
	private final Item _item;
	private final Location _loc;

	public OnPlayerItemDrop(Player player, Item item, Location loc)
	{
		this._player = player;
		this._item = item;
		this._loc = loc;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public Item getItem()
	{
		return this._item;
	}

	public Location getLocation()
	{
		return this._loc;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_ITEM_DROP;
	}
}
