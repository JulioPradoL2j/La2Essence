package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class VehicleCheckLocation extends ServerPacket
{
	private final Creature _boat;

	public VehicleCheckLocation(Creature boat)
	{
		this._boat = boat;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.VEHICLE_CHECK_LOCATION.writeId(this, buffer);
		buffer.writeInt(this._boat.getObjectId());
		buffer.writeInt(this._boat.getX());
		buffer.writeInt(this._boat.getY());
		buffer.writeInt(this._boat.getZ());
		buffer.writeInt(this._boat.getHeading());
	}
}
