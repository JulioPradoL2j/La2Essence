package org.l2jmobius.gameserver.network.clientpackets.balthusevent;

import org.l2jmobius.gameserver.managers.events.BalthusEventManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.balthusevent.ExBalthusEvent;

public class RequestEventBalthusToken extends ClientPacket
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
			int count = player.getVariables().getInt("BALTHUS_REWARD", 0);
			if (count != 0)
			{
				if (player.addItem(ItemProcessType.COMPENSATE, BalthusEventManager.getInstance().getConsolation().getId(), count, player, true) != null)
				{
					player.getVariables().set("BALTHUS_REWARD", 0);
					player.sendPacket(new ExBalthusEvent(player));
				}
			}
			else
			{
				player.sendPacket(SystemMessageId.NO_SIBI_S_COINS_AVAILABLE);
			}
		}
	}
}
