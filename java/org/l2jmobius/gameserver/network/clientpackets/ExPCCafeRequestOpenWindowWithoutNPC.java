package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.custom.PremiumSystemConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;

public class ExPCCafeRequestOpenWindowWithoutNPC extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && PremiumSystemConfig.PC_CAFE_ENABLED)
		{
			NpcHtmlMessage html = new NpcHtmlMessage();
			html.setFile(player, "data/html/pccafe.htm");
			player.sendPacket(html);
		}
	}
}
