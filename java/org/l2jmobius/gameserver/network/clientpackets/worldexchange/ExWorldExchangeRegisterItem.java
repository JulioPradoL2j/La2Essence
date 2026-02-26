package org.l2jmobius.gameserver.network.clientpackets.worldexchange;

import org.l2jmobius.gameserver.config.WorldExchangeConfig;
import org.l2jmobius.gameserver.managers.WorldExchangeManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExWorldExchangeRegisterItem extends ClientPacket
{
	private long _price;
	private int _itemId;
	private long _amount;

	@Override
	protected void readImpl()
	{
		this._price = this.readLong();
		this._itemId = this.readInt();
		this._amount = this.readLong();
	}

	@Override
	protected void runImpl()
	{
		if (WorldExchangeConfig.ENABLE_WORLD_EXCHANGE)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				WorldExchangeManager.getInstance().registerItemBid(player, this._itemId, this._amount, this._price);
			}
		}
	}
}
