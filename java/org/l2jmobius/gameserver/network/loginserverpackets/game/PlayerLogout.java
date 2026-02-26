package org.l2jmobius.gameserver.network.loginserverpackets.game;

import org.l2jmobius.commons.network.base.BaseWritablePacket;

public class PlayerLogout extends BaseWritablePacket
{
	 
	private final String _player;

	public PlayerLogout(String player)
	{
		this._player = player != null ? player : "";
	}

	@Override
	public void write()
	{
		this.writeByte(3);
		this.writeString(this._player);
	}
}
