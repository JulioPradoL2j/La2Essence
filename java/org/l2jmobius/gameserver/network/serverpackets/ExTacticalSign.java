package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExTacticalSign extends ServerPacket
{
	private final Creature _target;
	private final int _tokenId;

	public ExTacticalSign(Creature target, int tokenId)
	{
		this._target = target;
		this._tokenId = tokenId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_TACTICAL_SIGN.writeId(this, buffer);
		buffer.writeInt(this._target.getObjectId());
		buffer.writeInt(this._tokenId);
	}
}
