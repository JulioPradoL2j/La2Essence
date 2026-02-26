package org.l2jmobius.gameserver.network.clientpackets.settings;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.settings.ExUISetting;

public class RequestKeyMapping extends ClientPacket
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
			if (PlayerConfig.STORE_UI_SETTINGS)
			{
				player.sendPacket(new ExUISetting(player));
			}
		}
	}
}
