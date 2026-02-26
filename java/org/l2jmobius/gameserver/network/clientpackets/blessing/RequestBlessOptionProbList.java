package org.l2jmobius.gameserver.network.clientpackets.blessing;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.blessing.ExBlessOptionProbList;

public class RequestBlessOptionProbList extends ClientPacket
{
	private int _scrollId;
	private int _itemId;

	@Override
	protected void readImpl()
	{
		this._scrollId = this.readInt();
		this._itemId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExBlessOptionProbList(this._scrollId, this._itemId));
		}
	}
}
