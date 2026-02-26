package org.l2jmobius.gameserver.model.events.holders.item;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.item.instance.Item;

public class OnItemCreate implements IBaseEvent
{
	private final Item _item;
	private final Creature _creature;
	private final Object _reference;

	public OnItemCreate(Item item, Creature actor, Object reference)
	{
		this._item = item;
		this._creature = actor;
		this._reference = reference;
	}

	public Item getItem()
	{
		return this._item;
	}

	public Creature getActiveChar()
	{
		return this._creature;
	}

	public Object getReference()
	{
		return this._reference;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_ITEM_CREATE;
	}
}
