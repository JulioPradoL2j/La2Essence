package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventDispatcher;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.actor.player.OnPlayerChangeToAwakenedClass;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;

public class RequestChangeToAwakenedClass extends ClientPacket
{
	private boolean _change;

	@Override
	protected void readImpl()
	{
		this._change = this.readInt() == 1;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._change)
			{
				if (EventDispatcher.getInstance().hasListener(EventType.ON_PLAYER_CHANGE_TO_AWAKENED_CLASS, player))
				{
					EventDispatcher.getInstance().notifyEventAsync(new OnPlayerChangeToAwakenedClass(player), player);
				}
			}
			else
			{
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
	}
}
