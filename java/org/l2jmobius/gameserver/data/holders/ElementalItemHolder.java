package org.l2jmobius.gameserver.data.holders;

import org.l2jmobius.gameserver.model.actor.enums.creature.AttributeType;
import org.l2jmobius.gameserver.model.item.enums.ElementalItemType;

public class ElementalItemHolder
{
	private final int _itemId;
	private final AttributeType _element;
	private final ElementalItemType _type;
	private final int _power;

	public ElementalItemHolder(int itemId, AttributeType element, ElementalItemType type, int power)
	{
		this._itemId = itemId;
		this._element = element;
		this._type = type;
		this._power = power;
	}

	public int getItemId()
	{
		return this._itemId;
	}

	public AttributeType getElement()
	{
		return this._element;
	}

	public ElementalItemType getType()
	{
		return this._type;
	}

	public int getPower()
	{
		return this._power;
	}
}
