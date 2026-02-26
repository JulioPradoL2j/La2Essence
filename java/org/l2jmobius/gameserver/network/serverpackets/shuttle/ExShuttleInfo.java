package org.l2jmobius.gameserver.network.serverpackets.shuttle;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.instance.Shuttle;
import org.l2jmobius.gameserver.model.shuttle.ShuttleStop;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExShuttleInfo extends ServerPacket
{
	private final Shuttle _shuttle;
	private final List<ShuttleStop> _stops;

	public ExShuttleInfo(Shuttle shuttle)
	{
		this._shuttle = shuttle;
		this._stops = shuttle.getStops();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHUTTLE_INFO.writeId(this, buffer);
		buffer.writeInt(this._shuttle.getObjectId());
		buffer.writeInt(this._shuttle.getX());
		buffer.writeInt(this._shuttle.getY());
		buffer.writeInt(this._shuttle.getZ());
		buffer.writeInt(this._shuttle.getHeading());
		buffer.writeInt(this._shuttle.getId());
		buffer.writeInt(this._stops.size());

		for (ShuttleStop stop : this._stops)
		{
			buffer.writeInt(stop.getId());

			for (Location loc : stop.getDimensions())
			{
				buffer.writeInt(loc.getX());
				buffer.writeInt(loc.getY());
				buffer.writeInt(loc.getZ());
			}

			buffer.writeInt(stop.isDoorOpen());
			buffer.writeInt(stop.hasDoorChanged());
		}
	}
}
