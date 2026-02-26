package org.l2jmobius.gameserver.network.clientpackets.collection;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.collection.ExCollectionReceiveReward;

public class RequestCollectionReceiveReward extends ClientPacket
{
	private int _collectionId;

	@Override
	protected void readImpl()
	{
		this._collectionId = this.readShort();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getClient().getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExCollectionReceiveReward(this._collectionId, true));
		}
	}
}
