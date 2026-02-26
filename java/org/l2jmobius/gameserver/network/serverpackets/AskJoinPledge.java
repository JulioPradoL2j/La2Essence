package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class AskJoinPledge extends ServerPacket
{
	private final Player _requestor;
	private final String _pledgeName;

	public AskJoinPledge(Player requestor, String pledgeName)
	{
		this._requestor = requestor;
		this._pledgeName = pledgeName;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.ASK_JOIN_PLEDGE.writeId(this, buffer);
		buffer.writeInt(this._requestor.getObjectId());
		buffer.writeString(this._requestor.getName());
		buffer.writeString(this._pledgeName);
		buffer.writeInt(0);
		buffer.writeString("");
	}
}
