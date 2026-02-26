package org.l2jmobius.gameserver.network.loginserverpackets.login;

import org.l2jmobius.commons.network.base.BaseReadablePacket;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;

public class ChangePasswordResponse extends BaseReadablePacket
{
	public ChangePasswordResponse(byte[] decrypt)
	{
		super(decrypt);
		this.readByte();
		String character = this.readString();
		String msgToSend = this.readString();
		Player player = World.getInstance().getPlayer(character);
		if (player != null)
		{
			player.sendMessage(msgToSend);
		}
	}
}
