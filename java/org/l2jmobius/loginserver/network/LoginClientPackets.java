package org.l2jmobius.loginserver.network;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import org.l2jmobius.loginserver.network.clientpackets.AuthGameGuard;
import org.l2jmobius.loginserver.network.clientpackets.LoginClientPacket;
import org.l2jmobius.loginserver.network.clientpackets.RequestAuthLogin;
import org.l2jmobius.loginserver.network.clientpackets.RequestCmdLogin;
import org.l2jmobius.loginserver.network.clientpackets.RequestPIAgreement;
import org.l2jmobius.loginserver.network.clientpackets.RequestPIAgreementCheck;
import org.l2jmobius.loginserver.network.clientpackets.RequestServerList;
import org.l2jmobius.loginserver.network.clientpackets.RequestServerLogin;

public enum LoginClientPackets
{
	AUTH_GAME_GUARD(7, AuthGameGuard::new, ConnectionState.CONNECTED),
	REQUEST_AUTH_LOGIN(0, RequestAuthLogin::new, ConnectionState.AUTHED_GG),
	REQUEST_LOGIN(11, RequestCmdLogin::new, ConnectionState.AUTHED_GG),
	REQUEST_SERVER_LOGIN(2, RequestServerLogin::new, ConnectionState.AUTHED_LOGIN),
	REQUEST_SERVER_LIST(5, RequestServerList::new, ConnectionState.AUTHED_LOGIN),
	REQUEST_PI_AGREEMENT_CHECK(14, RequestPIAgreementCheck::new, ConnectionState.AUTHED_LOGIN),
	REQUEST_PI_AGREEMENT(15, RequestPIAgreement::new, ConnectionState.AUTHED_LOGIN);
	
	public static final LoginClientPackets[] PACKET_ARRAY;
	private final short _packetId;
	private final Supplier<LoginClientPacket> _packetSupplier;
	private final Set<ConnectionState> _connectionStates;
	
	private LoginClientPackets(int packetId, Supplier<LoginClientPacket> packetSupplier, ConnectionState... connectionStates)
	{
		if (packetId > 255)
		{
			throw new IllegalArgumentException("Packet id must not be bigger than 0xFF");
		}
		this._packetId = (short) packetId;
		this._packetSupplier = packetSupplier != null ? packetSupplier : () -> null;
		this._connectionStates = new HashSet<>(Arrays.asList(connectionStates));
	}
	
	public int getPacketId()
	{
		return this._packetId;
	}
	
	public LoginClientPacket newPacket()
	{
		return this._packetSupplier.get();
	}
	
	public Set<ConnectionState> getConnectionStates()
	{
		return this._connectionStates;
	}
	
	static
	{
		short maxPacketId = (short) Arrays.stream(values()).mapToInt(LoginClientPackets::getPacketId).max().orElse(0);
		PACKET_ARRAY = new LoginClientPackets[maxPacketId + 1];
		
		for (LoginClientPackets packet : values())
		{
			PACKET_ARRAY[packet.getPacketId()] = packet;
		}
	}
}
