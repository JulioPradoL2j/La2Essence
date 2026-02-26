package org.l2jmobius.gameserver.network.clientpackets.huntpass;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.huntpass.HuntPassInfo;
import org.l2jmobius.gameserver.network.serverpackets.huntpass.HuntPassSayhasSupportInfo;

public class RequestHuntPassInfo extends ClientPacket
{
	private int _passType;

	@Override
	protected void readImpl()
	{
		this._passType = this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new HuntPassInfo(player, this._passType));
			player.sendPacket(new HuntPassSayhasSupportInfo(player));
		}
	}
}
