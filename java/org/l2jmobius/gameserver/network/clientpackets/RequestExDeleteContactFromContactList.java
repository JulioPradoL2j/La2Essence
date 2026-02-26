package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.model.actor.Player;

public class RequestExDeleteContactFromContactList extends ClientPacket
{
	private String _name;

	@Override
	protected void readImpl()
	{
		this._name = this.readString();
	}

	@Override
	protected void runImpl()
	{
		if (GeneralConfig.ALLOW_MAIL)
		{
			if (this._name != null)
			{
				Player player = this.getPlayer();
				if (player != null)
				{
					player.getContactList().remove(this._name);
				}
			}
		}
	}
}
