package org.l2jmobius.gameserver.model.events.holders.item;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.item.instance.Item;

public class OnItemBypassEvent implements IBaseEvent
{
	private final Item _item;
	private final Player _player;
	private final String _event;

	public OnItemBypassEvent(Item item, Player player, String event)
	{
		this._item = item;
		this._player = player;
		this._event = event;
	}

	public Item getItem()
	{
		return this._item;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public String getEvent()
	{
		return this._event;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_ITEM_BYPASS_EVENT;
	}
}
