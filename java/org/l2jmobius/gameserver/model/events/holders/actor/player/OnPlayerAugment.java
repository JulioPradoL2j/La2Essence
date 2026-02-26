package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.VariationInstance;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.item.instance.Item;

public class OnPlayerAugment implements IBaseEvent
{
	private final Player _player;
	private final Item _item;
	private final VariationInstance _augmentation;
	private final boolean _isAugment;

	public OnPlayerAugment(Player player, Item item, VariationInstance augment, boolean isAugment)
	{
		this._player = player;
		this._item = item;
		this._augmentation = augment;
		this._isAugment = isAugment;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public Item getItem()
	{
		return this._item;
	}

	public VariationInstance getAugmentation()
	{
		return this._augmentation;
	}

	public boolean isAugment()
	{
		return this._isAugment;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_AUGMENT;
	}
}
