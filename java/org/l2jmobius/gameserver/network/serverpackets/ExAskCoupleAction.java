package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExAskCoupleAction extends ServerPacket
{
	private final int _objectId;
	private final int _actionId;

	public ExAskCoupleAction(int charObjId, int social)
	{
		this._objectId = charObjId;
		this._actionId = social;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ASK_COUPLE_ACTION.writeId(this, buffer);
		buffer.writeInt(this._actionId);
		buffer.writeInt(this._objectId);
	}
}
