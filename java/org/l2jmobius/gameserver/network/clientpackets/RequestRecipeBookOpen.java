package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.RecipeManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.SystemMessageId;

public class RequestRecipeBookOpen extends ClientPacket
{
	private boolean _isDwarvenCraft;

	@Override
	protected void readImpl()
	{
		this._isDwarvenCraft = this.readInt() == 0;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (player.isCastingNow())
			{
				player.sendPacket(SystemMessageId.YOUR_RECIPE_BOOK_MAY_NOT_BE_ACCESSED_WHILE_USING_A_SKILL);
			}
			else if (player.getActiveRequester() != null)
			{
				player.sendMessage("You may not alter your recipe book while trading.");
			}
			else
			{
				RecipeManager.getInstance().requestBookOpen(player, this._isDwarvenCraft);
			}
		}
	}
}
