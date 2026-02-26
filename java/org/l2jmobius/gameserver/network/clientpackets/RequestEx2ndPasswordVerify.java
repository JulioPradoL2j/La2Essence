package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.data.xml.SecondaryAuthData;

public class RequestEx2ndPasswordVerify extends ClientPacket
{
	private String _password;

	@Override
	protected void readImpl()
	{
		this._password = this.readString();
	}

	@Override
	protected void runImpl()
	{
		if (SecondaryAuthData.getInstance().isEnabled())
		{
			this.getClient().getSecondaryAuth().checkPassword(this._password, false);
		}
	}
}
