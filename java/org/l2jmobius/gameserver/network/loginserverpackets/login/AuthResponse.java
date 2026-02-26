package org.l2jmobius.gameserver.network.loginserverpackets.login;

import org.l2jmobius.commons.network.base.BaseReadablePacket;

public class AuthResponse extends BaseReadablePacket
{
	private final int _serverId;
	private final String _serverName;

	public AuthResponse(byte[] decrypt)
	{
		super(decrypt);
		this.readByte();
		this._serverId = this.readByte();
		this._serverName = this.readString();
	}

	public int getServerId()
	{
		return this._serverId;
	}

	public String getServerName()
	{
		return this._serverName;
	}
}
