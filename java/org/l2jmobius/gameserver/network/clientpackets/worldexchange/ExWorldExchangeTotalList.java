package org.l2jmobius.gameserver.network.clientpackets.worldexchange;

import java.util.LinkedList;
import java.util.List;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.worldexchange.WorldExchangeTotalList;

public class ExWorldExchangeTotalList extends ClientPacket
{
	private final List<Integer> itemIds = new LinkedList<>();

	@Override
	protected void readImpl()
	{
		int size = this.readInt();

		for (int index = 0; index < size; index++)
		{
			this.itemIds.add(this.readInt());
		}
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new WorldExchangeTotalList(this.itemIds));
		}
	}
}
