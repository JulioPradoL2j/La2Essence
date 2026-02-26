package org.l2jmobius.gameserver.network.clientpackets.settings;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.settings.ExItemAnnounceSetting;

public class ExSaveItemAnnounceSetting extends ClientPacket
{
	private boolean _announceType;

	@Override
	protected void readImpl()
	{
		this._announceType = this.readByte() == 1;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.getClientSettings().setAnnounceEnabled(this._announceType);
			player.sendPacket(new ExItemAnnounceSetting(this._announceType));
		}
	}
}
