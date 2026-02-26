package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class AutoAttackStart extends ServerPacket
{
	private final int _targetObjId;

	public AutoAttackStart(int targetId)
	{
		this._targetObjId = targetId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.COMBAT_MODE_START.writeId(this, buffer);
		buffer.writeInt(this._targetObjId);
	}

	@Override
	public boolean canBeDropped(GameClient client)
	{
		return true;
	}
}
