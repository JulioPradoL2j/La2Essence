package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExConfirmAddingContact;

public class RequestExAddContactToContactList extends ClientPacket
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
					boolean charAdded = player.getContactList().add(this._name);
					player.sendPacket(new ExConfirmAddingContact(this._name, charAdded));
				}
			}
		}
	}
}
