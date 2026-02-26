package org.l2jmobius.gameserver.network.serverpackets.prison;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPrisonUserDonation extends ServerPacket
{
	private final boolean _success;

	public ExPrisonUserDonation(boolean success)
	{
		this._success = success;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PRISON_USER_DONATION.writeId(this, buffer);
		buffer.writeByte(this._success);
	}
}
