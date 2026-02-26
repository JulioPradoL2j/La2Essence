package org.l2jmobius.gameserver.network.clientpackets.chatbackground;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExChatBackgroundSetting extends ClientPacket
{
	private boolean _enable;
	private int _currentBackground;

	@Override
	protected void readImpl()
	{
		this._currentBackground = this.readInt();
		this._enable = this.readByte() > 0;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.setChatBackground(this._enable, this._currentBackground);
		}
	}
}
