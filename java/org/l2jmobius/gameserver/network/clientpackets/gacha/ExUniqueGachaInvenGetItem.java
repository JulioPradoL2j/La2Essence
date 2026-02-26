package org.l2jmobius.gameserver.network.clientpackets.gacha;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.gameserver.managers.events.UniqueGachaManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.gacha.UniqueGachaInvenGetItem;

public class ExUniqueGachaInvenGetItem extends ClientPacket
{
	private List<ItemHolder> _requestedItems;

	@Override
	protected void readImpl()
	{
		int size = this.readInt();
		this._requestedItems = new ArrayList<>(size);

		for (int index = 0; index < size; index++)
		{
			this._requestedItems.add(new ItemHolder(this.readInt(), this.readLong()));
		}
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			boolean isSuccess = UniqueGachaManager.getInstance().receiveItemsFromTemporaryWarehouse(player, this._requestedItems);
			player.sendPacket(new UniqueGachaInvenGetItem(isSuccess));
		}
	}
}
