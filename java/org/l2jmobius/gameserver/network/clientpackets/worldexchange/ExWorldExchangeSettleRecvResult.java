package org.l2jmobius.gameserver.network.clientpackets.worldexchange;

import org.l2jmobius.gameserver.config.WorldExchangeConfig;
import org.l2jmobius.gameserver.managers.WorldExchangeManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExWorldExchangeSettleRecvResult extends ClientPacket
{
	private long _worldExchangeIndex;

	@Override
	protected void readImpl()
	{
		this._worldExchangeIndex = this.readLong();
	}

	@Override
	protected void runImpl()
	{
		if (WorldExchangeConfig.ENABLE_WORLD_EXCHANGE)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				WorldExchangeManager.getInstance().getItemStatusAndMakeAction(player, this._worldExchangeIndex);
			}
		}
	}
}
