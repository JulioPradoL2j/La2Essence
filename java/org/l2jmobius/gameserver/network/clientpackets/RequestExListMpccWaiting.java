package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExListMpccWaiting;

public class RequestExListMpccWaiting extends ClientPacket
{
	private int _page;
	private int _location;
	private int _level;

	@Override
	protected void readImpl()
	{
		this._page = this.readInt();
		this._location = this.readInt();
		this._level = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExListMpccWaiting(this._page, this._location, this._level));
		}
	}
}
