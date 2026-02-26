package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.handler.AdminCommandHandler;
import org.l2jmobius.gameserver.handler.BypassHandler;
import org.l2jmobius.gameserver.handler.IBypassHandler;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventDispatcher;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.actor.player.OnPlayerBypass;

public class RequestTutorialPassCmdToServer extends ClientPacket
{
	private String _bypass = null;

	@Override
	protected void readImpl()
	{
		this._bypass = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._bypass.startsWith("admin_"))
			{
				AdminCommandHandler.getInstance().onCommand(player, this._bypass, true);
			}
			else
			{
				IBypassHandler handler = BypassHandler.getInstance().getHandler(this._bypass);
				if (handler != null)
				{
					handler.onCommand(this._bypass, player, null);
				}
			}

			if (EventDispatcher.getInstance().hasListener(EventType.ON_PLAYER_BYPASS, player))
			{
				EventDispatcher.getInstance().notifyEventAsync(new OnPlayerBypass(player, this._bypass), player);
			}
		}
	}
}
