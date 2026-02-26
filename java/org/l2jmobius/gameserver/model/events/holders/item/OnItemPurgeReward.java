package org.l2jmobius.gameserver.model.events.holders.item;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.item.instance.Item;

public class OnItemPurgeReward implements IBaseEvent
{
	private final Player _player;
	private final Item _item;

	public OnItemPurgeReward(Player player, Item item)
	{
		this._player = player;
		this._item = item;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public Item getItem()
	{
		return this._item;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_ITEM_PURGE_REWARD;
	}
}
