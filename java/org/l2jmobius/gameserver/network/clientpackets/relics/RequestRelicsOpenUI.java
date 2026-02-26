package org.l2jmobius.gameserver.network.clientpackets.relics;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.PlayerRelicData;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.relics.ExRelicsActiveInfo;
import org.l2jmobius.gameserver.network.serverpackets.relics.ExRelicsList;

public class RequestRelicsOpenUI extends ClientPacket
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
			int activeRelicId = 0;
			int activeRelicLevel = 0;

			for (PlayerRelicData relic : player.getRelics())
			{
				if (relic.getRelicId() == player.getVariables().getInt("ACTIVE_RELIC", 0))
				{
					activeRelicId = relic.getRelicId();
					activeRelicLevel = relic.getRelicLevel();
					break;
				}
			}

			player.sendPacket(new ExRelicsActiveInfo(activeRelicId, activeRelicLevel));
			player.getVariables().set("ACTIVE_RELIC", activeRelicId);
			player.getVariables().storeMe();
			player.sendPacket(new ExRelicsList(player));
		}
	}
}
