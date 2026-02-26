package org.l2jmobius.loginserver.network.clientpackets;

import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;

import org.l2jmobius.loginserver.GameServerTable;
import org.l2jmobius.loginserver.LoginController;
import org.l2jmobius.loginserver.config.LoginConfig;
import org.l2jmobius.loginserver.enums.AccountKickedReason;
import org.l2jmobius.loginserver.enums.LoginFailReason;
import org.l2jmobius.loginserver.model.data.AccountInfo;
import org.l2jmobius.loginserver.network.ConnectionState;
import org.l2jmobius.loginserver.network.LoginClient;
import org.l2jmobius.loginserver.network.serverpackets.AccountKicked;
import org.l2jmobius.loginserver.network.serverpackets.LoginOk;
import org.l2jmobius.loginserver.network.serverpackets.ServerList;

public class RequestCmdLogin extends LoginClientPacket
{
	private static final Logger LOGGER = Logger.getLogger(RequestCmdLogin.class.getName());
	private final byte[] _raw = new byte[128];
	
	@Override
	protected boolean readImpl()
	{
		if (this.remaining() >= 128)
		{
			this.readInt();
			this.readBytes(this._raw);
			return true;
		}
		return false;
	}
	
	@Override
	public void run()
	{
		if (LoginConfig.ENABLE_CMD_LINE_LOGIN)
		{
			LoginClient client = this.getClient();
			byte[] decrypted = new byte[128];
			
			try
			{
				Cipher rsaCipher = Cipher.getInstance("RSA/ECB/nopadding");
				rsaCipher.init(2, client.getRSAPrivateKey());
				rsaCipher.doFinal(this._raw, 0, 128, decrypted, 0);
			}
			catch (GeneralSecurityException var10)
			{
				LOGGER.log(Level.INFO, "", var10);
				return;
			}
			
			String password;
			String user;
			try
			{
				user = new String(decrypted, 64, 14).trim();
				password = new String(decrypted, 96, 16).trim();
			}
			catch (Exception var9)
			{
				LOGGER.log(Level.WARNING, "", var9);
				return;
			}
			
			String clientAddr = client.getIp();
			LoginController lc = LoginController.getInstance();
			AccountInfo info = lc.retriveAccountInfo(clientAddr, user, password);
			if (info == null)
			{
				client.close(LoginFailReason.REASON_ACCESS_FAILED);
			}
			else
			{
				switch (lc.tryCheckinAccount(client, clientAddr, info))
				{
					case AUTH_SUCCESS:
						client.setAccount(info.getLogin());
						client.setConnectionState(ConnectionState.AUTHED_LOGIN);
						client.setSessionKey(lc.assignSessionKeyToClient(info.getLogin(), client));
						lc.getCharactersOnAccount(info.getLogin());
						if (LoginConfig.SHOW_LICENCE)
						{
							client.sendPacket(new LoginOk(client.getSessionKey()));
						}
						else
						{
							client.sendPacket(new ServerList(client));
						}
						break;
					case INVALID_PASSWORD:
						client.close(LoginFailReason.REASON_USER_OR_PASS_WRONG);
						break;
					case ACCOUNT_BANNED:
						client.close(new AccountKicked(AccountKickedReason.REASON_PERMANENTLY_BANNED));
						return;
					case ALREADY_ON_LS:
						LoginClient oldClient = lc.getAuthedClient(info.getLogin());
						if (oldClient != null)
						{
							oldClient.close(LoginFailReason.REASON_ACCOUNT_IN_USE);
							lc.removeAuthedLoginClient(info.getLogin());
						}
						
						client.close(LoginFailReason.REASON_ACCOUNT_IN_USE);
						break;
					case ALREADY_ON_GS:
						GameServerTable.GameServerInfo gsi = lc.getAccountOnGameServer(info.getLogin());
						if (gsi != null)
						{
							client.close(LoginFailReason.REASON_ACCOUNT_IN_USE);
							if (gsi.isAuthed())
							{
								gsi.getGameServerThread().kickPlayer(info.getLogin());
							}
						}
				}
			}
		}
	}
}
