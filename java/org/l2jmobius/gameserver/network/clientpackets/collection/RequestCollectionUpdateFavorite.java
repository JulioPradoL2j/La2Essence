package org.l2jmobius.gameserver.network.clientpackets.collection;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.collection.ExCollectionUpdateFavorite;

public class RequestCollectionUpdateFavorite extends ClientPacket
{
	private int _isAdd;
	private int _collectionId;

	@Override
	protected void readImpl()
	{
		this._isAdd = this.readByte();
		this._collectionId = this.readShort();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._isAdd == 1)
			{
				player.addCollectionFavorite(this._collectionId);
			}
			else
			{
				player.removeCollectionFavorite(this._collectionId);
			}

			player.sendPacket(new ExCollectionUpdateFavorite(this._isAdd, this._collectionId));
		}
	}
}
