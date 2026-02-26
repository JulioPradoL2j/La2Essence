package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.handler.AdminCommandHandler;
import org.l2jmobius.gameserver.handler.BypassHandler;
import org.l2jmobius.gameserver.handler.IBypassHandler;
import org.l2jmobius.gameserver.model.actor.Player;

public class RequestTutorialLinkHtml extends ClientPacket
{
	private String _bypass;

	@Override
	protected void readImpl()
	{
		this.readInt();
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
		}
	}
}
