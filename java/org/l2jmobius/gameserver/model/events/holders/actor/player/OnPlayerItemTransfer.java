package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.itemcontainer.ItemContainer;

public class OnPlayerItemTransfer implements IBaseEvent
{
	private final Player _player;
	private final Item _item;
	private final ItemContainer _container;

	public OnPlayerItemTransfer(Player player, Item item, ItemContainer container)
	{
		this._player = player;
		this._item = item;
		this._container = container;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public Item getItem()
	{
		return this._item;
	}

	public ItemContainer getContainer()
	{
		return this._container;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_ITEM_TRANSFER;
	}
}
