package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExStopMoveInAirShip extends ServerPacket
{
	private final Player _player;
	private final int _shipObjId;
	private final int _h;
	private final Location _loc;

	public ExStopMoveInAirShip(Player player, int shipObjId)
	{
		this._player = player;
		this._shipObjId = shipObjId;
		this._h = player.getHeading();
		this._loc = player.getInVehiclePosition();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_STOP_MOVE_IN_AIRSHIP.writeId(this, buffer);
		buffer.writeInt(this._player.getObjectId());
		buffer.writeInt(this._shipObjId);
		buffer.writeInt(this._loc.getX());
		buffer.writeInt(this._loc.getY());
		buffer.writeInt(this._loc.getZ());
		buffer.writeInt(this._h);
	}
}
