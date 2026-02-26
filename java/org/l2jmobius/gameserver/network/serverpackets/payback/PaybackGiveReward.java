package org.l2jmobius.gameserver.network.serverpackets.payback;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class PaybackGiveReward extends ServerPacket
{
	private final boolean _status;
	private final int _eventID;
	private final int _index;

	public PaybackGiveReward(boolean status, int eventID, int index)
	{
		this._status = status;
		this._eventID = eventID;
		this._index = index;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PAYBACK_GIVE_REWARD.writeId(this, buffer);
		buffer.writeByte(this._status);
		buffer.writeByte(this._eventID);
		buffer.writeInt(this._index);
	}
}
