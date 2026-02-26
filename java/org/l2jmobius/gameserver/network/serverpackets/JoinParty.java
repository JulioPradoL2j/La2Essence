package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class JoinParty extends ServerPacket
{
	private final int _response;
	private final int _type;

	public JoinParty(int response, Player requestor)
	{
		this._response = response;
		this._type = requestor.getClientSettings().getPartyContributionType();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.JOIN_PARTY.writeId(this, buffer);
		buffer.writeInt(this._response);
		buffer.writeInt(this._type);
		if (this._type != 0)
		{
			buffer.writeInt(0);
			buffer.writeInt(0);
		}
	}
}
