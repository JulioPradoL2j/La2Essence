package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExAskJoinMPCC extends ServerPacket
{
	private final String _requestorName;

	public ExAskJoinMPCC(String requestorName)
	{
		this._requestorName = requestorName;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ASK_JOIN_MPCC.writeId(this, buffer);
		buffer.writeString(this._requestorName);
		buffer.writeInt(0);
	}
}
