package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.groups.PartyDistributionType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExSetPartyLooting extends ServerPacket
{
	private final int _result;
	private final PartyDistributionType _partyDistributionType;

	public ExSetPartyLooting(int result, PartyDistributionType partyDistributionType)
	{
		this._result = result;
		this._partyDistributionType = partyDistributionType;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SET_PARTY_LOOTING.writeId(this, buffer);
		buffer.writeInt(this._result);
		buffer.writeInt(this._partyDistributionType.getId());
	}
}
