package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExTeleportToLocationActivate extends ServerPacket
{
	private final int _objectId;
	private final Location _loc;

	public ExTeleportToLocationActivate(Creature creature)
	{
		this._objectId = creature.getObjectId();
		this._loc = creature.getLocation();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_TELEPORT_TO_LOCATION_ACTIVATE.writeId(this, buffer);
		buffer.writeInt(this._objectId);
		buffer.writeInt(this._loc.getX());
		buffer.writeInt(this._loc.getY());
		buffer.writeInt(this._loc.getZ());
		buffer.writeInt(0);
		buffer.writeInt(this._loc.getHeading());
		buffer.writeInt(0);
	}
}
