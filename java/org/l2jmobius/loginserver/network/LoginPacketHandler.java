package org.l2jmobius.loginserver.network;

import java.util.logging.Logger;

import org.l2jmobius.commons.network.PacketHandler;
import org.l2jmobius.commons.network.ReadableBuffer;
import org.l2jmobius.commons.network.ReadablePacket;
import org.l2jmobius.commons.util.TraceUtil;
import org.l2jmobius.loginserver.enums.LoginFailReason;

public class LoginPacketHandler implements PacketHandler<LoginClient>
{
	private static final Logger LOGGER = Logger.getLogger(LoginPacketHandler.class.getName());
	
	@Override
	public ReadablePacket<LoginClient> handlePacket(ReadableBuffer buffer, LoginClient client)
	{
		int packetId;
		try
		{
			packetId = Byte.toUnsignedInt(buffer.readByte());
		}
		catch (Exception var5)
		{
			LOGGER.warning("LoginPacketHandler: Problem receiving packet id from " + client);
			LOGGER.warning(TraceUtil.getStackTrace(var5));
			client.close(LoginFailReason.REASON_ACCESS_FAILED);
			return null;
		}
		
		if (packetId >= 0 && packetId < LoginClientPackets.PACKET_ARRAY.length)
		{
			LoginClientPackets packetEnum = LoginClientPackets.PACKET_ARRAY[packetId];
			if (packetEnum == null)
			{
				return null;
			}
			return !packetEnum.getConnectionStates().contains(client.getConnectionState()) ? null : packetEnum.newPacket();
		}
		return null;
	}
}
