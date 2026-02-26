package org.l2jmobius.gameserver.network.clientpackets.attributechange;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.attributechange.ExChangeAttributeFail;

public class RequestChangeAttributeCancel extends ClientPacket
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
			player.sendPacket(ExChangeAttributeFail.STATIC);
		}
	}
}
