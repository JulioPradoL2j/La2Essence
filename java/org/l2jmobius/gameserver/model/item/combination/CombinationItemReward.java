package org.l2jmobius.gameserver.model.item.combination;

import org.l2jmobius.gameserver.model.item.holders.ItemEnchantHolder;

public class CombinationItemReward extends ItemEnchantHolder
{
	private final CombinationItemType _type;

	public CombinationItemReward(int id, int count, CombinationItemType type, int enchant)
	{
		super(id, count, enchant);
		this._type = type;
	}

	public CombinationItemType getType()
	{
		return this._type;
	}
}
