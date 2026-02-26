package org.l2jmobius.gameserver.network.serverpackets.shuttle;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Shuttle;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExShuttleGetOff extends ServerPacket
{
	private final int _playerObjectId;
	private final int _shuttleObjectId;
	private final int _x;
	private final int _y;
	private final int _z;

	public ExShuttleGetOff(Player player, Shuttle shuttle, int x, int y, int z)
	{
		this._playerObjectId = player.getObjectId();
		this._shuttleObjectId = shuttle.getObjectId();
		this._x = x;
		this._y = y;
		this._z = z;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_GETOFF_SHUTTLE.writeId(this, buffer);
		buffer.writeInt(this._playerObjectId);
		buffer.writeInt(this._shuttleObjectId);
		buffer.writeInt(this._x);
		buffer.writeInt(this._y);
		buffer.writeInt(this._z);
	}
}
