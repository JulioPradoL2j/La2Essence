package org.l2jmobius.loginserver.network.clientpackets;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.l2jmobius.commons.network.ReadablePacket;
import org.l2jmobius.loginserver.network.LoginClient;

public abstract class LoginClientPacket extends ReadablePacket<LoginClient>
{
	private static final Logger LOGGER = Logger.getLogger(LoginClientPacket.class.getName());
	
	@Override
	protected boolean read()
	{
		try
		{
			return this.readImpl();
		}
		catch (Exception var4)
		{
			if (LOGGER.isLoggable(Level.SEVERE))
			{
				LoginClient client = this.getClient();
				String clientDescription = client != null ? client.toString() : "unknown client";
				LOGGER.log(Level.SEVERE, "Error while reading login packet " + this.getClass().getSimpleName() + " from " + clientDescription + ".", var4);
			}
			
			return false;
		}
	}
	
	protected abstract boolean readImpl();
}
