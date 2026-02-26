package org.l2jmobius.gameserver.network.serverpackets.pledgeV3;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAllianceCreateResult extends ServerPacket
{
	private final int _nResult;

	public ExAllianceCreateResult(int nResult)
	{
		this._nResult = nResult;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ALLIANCE_CREATE.writeId(this, buffer);
		buffer.writeInt(this._nResult);
	}
}
