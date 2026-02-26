package org.l2jmobius.loginserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.commons.network.WritablePacket;
import org.l2jmobius.loginserver.network.LoginClient;

public abstract class LoginServerPacket extends WritablePacket<LoginClient>
{
	@Override
	protected boolean write(LoginClient client, WritableBuffer buffer)
	{
		try
		{
			this.writeImpl(client, buffer);
			return true;
		}
		catch (Exception var4)
		{
			return false;
		}
	}
	
	protected abstract void writeImpl(LoginClient var1, WritableBuffer var2);
}
