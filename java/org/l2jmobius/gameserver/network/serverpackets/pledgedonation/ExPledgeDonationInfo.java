package org.l2jmobius.gameserver.network.serverpackets.pledgedonation;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPledgeDonationInfo extends ServerPacket
{
	private final int _curPoints;
	private final boolean _accepted;

	public ExPledgeDonationInfo(int curPoints, boolean accepted)
	{
		this._curPoints = curPoints;
		this._accepted = accepted;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_DONATION_INFO.writeId(this, buffer);
		buffer.writeInt(this._curPoints);
		buffer.writeByte(!this._accepted);
	}
}
