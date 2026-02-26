package org.l2jmobius.gameserver.network.clientpackets.sayune;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.SayuneRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestFlyMove extends ClientPacket
{
	private int _locationId;

	@Override
	protected void readImpl()
	{
		this._locationId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			SayuneRequest request = player.getRequest(SayuneRequest.class);
			if (request != null)
			{
				request.move(player, this._locationId);
			}
		}
	}
}
