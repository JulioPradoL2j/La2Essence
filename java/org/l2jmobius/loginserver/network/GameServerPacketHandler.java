package org.l2jmobius.loginserver.network;

import java.util.logging.Logger;

import org.l2jmobius.commons.network.base.BaseReadablePacket;
import org.l2jmobius.loginserver.GameServerThread;
import org.l2jmobius.loginserver.network.gameserverpackets.BlowFishKey;
import org.l2jmobius.loginserver.network.gameserverpackets.ChangeAccessLevel;
import org.l2jmobius.loginserver.network.gameserverpackets.ChangePassword;
import org.l2jmobius.loginserver.network.gameserverpackets.GameServerAuth;
import org.l2jmobius.loginserver.network.gameserverpackets.PlayerAuthRequest;
import org.l2jmobius.loginserver.network.gameserverpackets.PlayerInGame;
import org.l2jmobius.loginserver.network.gameserverpackets.PlayerLogout;
import org.l2jmobius.loginserver.network.gameserverpackets.PlayerTracert;
import org.l2jmobius.loginserver.network.gameserverpackets.ReplyCharacters;
import org.l2jmobius.loginserver.network.gameserverpackets.RequestTempBan;
import org.l2jmobius.loginserver.network.gameserverpackets.ServerStatus;

public class GameServerPacketHandler
{
	private static final Logger LOGGER = Logger.getLogger(GameServerPacketHandler.class.getName());
	
	private GameServerPacketHandler()
	{
	}
	
	public static BaseReadablePacket handlePacket(byte[] data, GameServerThread server)
	{
		if (data != null && data.length != 0)
		{
			int opcode = data[0] & 255;
			GameServerPacketHandler.GameServerState state = server.getLoginConnectionState();
			switch (state)
			{
				case CONNECTED:
					switch (opcode)
					{
						case 0:
							return new BlowFishKey(data, server);
						default:
							logInvalidOpcode(opcode, state, server);
							return null;
					}
				case BF_CONNECTED:
					switch (opcode)
					{
						case 1:
							return new GameServerAuth(data, server);
						default:
							logInvalidOpcode(opcode, state, server);
							return null;
					}
				case AUTHED:
					switch (opcode)
					{
						case 2:
							return new PlayerInGame(data, server);
						case 3:
							return new PlayerLogout(data, server);
						case 4:
							return new ChangeAccessLevel(data, server);
						case 5:
							return new PlayerAuthRequest(data, server);
						case 6:
							return new ServerStatus(data, server);
						case 7:
							return new PlayerTracert(data);
						case 8:
							return new ReplyCharacters(data, server);
						case 9:
							return null;
						case 10:
							return new RequestTempBan(data);
						case 11:
							new ChangePassword(data);
							return null;
						default:
							logInvalidOpcode(opcode, state, server);
							return null;
					}
				default:
					LOGGER.warning("Unknown state " + state + " from " + server);
					server.forceClose(6);
					return null;
			}
		}
		LOGGER.warning("Received empty packet from " + server);
		server.forceClose(6);
		return null;
	}
	
	private static void logInvalidOpcode(int opcode, GameServerPacketHandler.GameServerState state, GameServerThread server)
	{
		LOGGER.warning("Unknown opcode (" + Integer.toHexString(opcode).toUpperCase() + ") in state " + state + " from " + server + ", closing connection.");
		server.forceClose(6);
	}
	
	public static enum GameServerState
	{
		CONNECTED,
		BF_CONNECTED,
		AUTHED;
	}
}
