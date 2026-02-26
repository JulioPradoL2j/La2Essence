package org.l2jmobius.gameserver.network.clientpackets.gacha;

import java.util.List;

import org.l2jmobius.gameserver.managers.events.UniqueGachaManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.holders.GachaItemTimeStampHolder;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.gacha.UniqueGachaHistory;

public class ExUniqueGachaHistory extends ClientPacket
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
			List<GachaItemTimeStampHolder> itemHistory = UniqueGachaManager.getInstance().getGachaCharacterHistory(player);
			player.sendPacket(new UniqueGachaHistory(itemHistory));
		}
	}
}
