package org.l2jmobius.loginserver.network.clientpackets;

import org.l2jmobius.loginserver.LoginController;
import org.l2jmobius.loginserver.LoginServer;
import org.l2jmobius.loginserver.SessionKey;
import org.l2jmobius.loginserver.config.LoginConfig;
import org.l2jmobius.loginserver.enums.LoginFailReason;
import org.l2jmobius.loginserver.enums.PlayFailReason;
import org.l2jmobius.loginserver.network.LoginClient;
import org.l2jmobius.loginserver.network.serverpackets.PlayOk;

public class RequestServerLogin extends LoginClientPacket
{
	private int _skey1;
	private int _skey2;
	private int _serverId;
	
	@Override
	protected boolean readImpl()
	{
		if (this.remaining() >= 9)
		{
			this._skey1 = this.readInt();
			this._skey2 = this.readInt();
			this._serverId = this.readByte();
			return true;
		}
		return false;
	}
	
	@Override
	public void run()
	{
		LoginClient client = this.getClient();
		SessionKey sk = client.getSessionKey();
		if (LoginConfig.SHOW_LICENCE && !sk.checkLoginPair(this._skey1, this._skey2))
		{
			client.close(LoginFailReason.REASON_ACCESS_FAILED);
		}
		else if (LoginServer.getInstance().getStatus() != 4 && (LoginServer.getInstance().getStatus() != 5 || client.getAccessLevel() >= 1))
		{
			if (LoginController.getInstance().isLoginPossible(client, this._serverId))
			{
				client.setJoinedGS(true);
				client.sendPacket(new PlayOk(sk));
			}
			else
			{
				client.close(PlayFailReason.REASON_SERVER_OVERLOADED);
			}
		}
		else
		{
			client.close(LoginFailReason.REASON_ACCESS_FAILED);
		}
	}
}
