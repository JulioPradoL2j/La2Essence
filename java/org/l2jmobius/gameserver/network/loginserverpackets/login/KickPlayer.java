package org.l2jmobius.gameserver.network.loginserverpackets.login;

import org.l2jmobius.commons.network.base.BaseReadablePacket;

public class KickPlayer extends BaseReadablePacket
{
	private final String _account;

	public KickPlayer(byte[] decrypt)
	{
		super(decrypt);
		this.readByte();
		this._account = this.readString();
	}

	public String getAccount()
	{
		return this._account;
	}
}
