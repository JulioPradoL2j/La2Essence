package org.l2jmobius.gameserver.model.actor.request;

import org.l2jmobius.gameserver.model.actor.Player;

public class RandomCraftRequest extends AbstractRequest
{
	public RandomCraftRequest(Player player)
	{
		super(player);
	}

	@Override
	public boolean isUsing(int objectId)
	{
		return false;
	}
}
