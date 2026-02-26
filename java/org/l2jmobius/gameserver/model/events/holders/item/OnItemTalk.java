package org.l2jmobius.gameserver.model.events.holders.item;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.item.instance.Item;

public class OnItemTalk implements IBaseEvent
{
	private final Item _item;
	private final Player _player;

	public OnItemTalk(Item item, Player player)
	{
		this._item = item;
		this._player = player;
	}

	public Item getItem()
	{
		return this._item;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_ITEM_TALK;
	}
}
