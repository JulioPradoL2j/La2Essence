package org.l2jmobius.gameserver.network.serverpackets.newcrest;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class GetPledgeCrestPreset extends ServerPacket
{
	private final int _clanId;
	private final int _emblemId;

	public GetPledgeCrestPreset(int pledgeId, int crestId)
	{
		this._clanId = pledgeId;
		this._emblemId = crestId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_GET_PLEDGE_CREST_PRESET.writeId(this, buffer);
		buffer.writeInt(1);
		buffer.writeInt(this._clanId);
		buffer.writeInt(this._emblemId);
	}
}
