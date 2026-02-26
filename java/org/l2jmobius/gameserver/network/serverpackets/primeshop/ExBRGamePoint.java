package org.l2jmobius.gameserver.network.serverpackets.primeshop;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExBRGamePoint extends ServerPacket
{
	private final int _charId;
	private final int _charPoints;

	public ExBRGamePoint(Player player)
	{
		this._charId = player.getObjectId();
		this._charPoints = player.getPrimePoints();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BR_NOTIFY_GAME_POINT.writeId(this, buffer);
		buffer.writeInt(this._charId);
		buffer.writeLong(this._charPoints);
		buffer.writeInt(0);
	}
}
