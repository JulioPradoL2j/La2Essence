package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExReplyPostItemList;

public class RequestPostItemList extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		if (GeneralConfig.ALLOW_MAIL && GeneralConfig.ALLOW_ATTACHMENTS)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				player.sendPacket(new ExReplyPostItemList(1, player));
				player.sendPacket(new ExReplyPostItemList(2, player));
			}
		}
	}
}
