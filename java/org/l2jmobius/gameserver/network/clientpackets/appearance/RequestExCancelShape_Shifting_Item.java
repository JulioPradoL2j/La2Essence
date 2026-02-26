package org.l2jmobius.gameserver.network.clientpackets.appearance;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.ShapeShiftingItemRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.appearance.ExShapeShiftingResult;

public class RequestExCancelShape_Shifting_Item extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.removeRequest(ShapeShiftingItemRequest.class);
			player.sendPacket(ExShapeShiftingResult.FAILED);
		}
	}
}
