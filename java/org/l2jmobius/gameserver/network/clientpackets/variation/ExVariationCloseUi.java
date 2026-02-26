package org.l2jmobius.gameserver.network.clientpackets.variation;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.VariationRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExVariationCloseUi extends ClientPacket
{
	@Override
	protected void readImpl()
	{
		this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (player.getRequest(VariationRequest.class) != null)
			{
				player.removeRequest(VariationRequest.class);
			}
		}
	}
}
