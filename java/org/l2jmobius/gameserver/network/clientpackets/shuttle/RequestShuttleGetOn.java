package org.l2jmobius.gameserver.network.clientpackets.shuttle;

import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Shuttle;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestShuttleGetOn extends ClientPacket
{
	private int _x;
	private int _y;
	private int _z;

	@Override
	protected void readImpl()
	{
		this.readInt();
		this._x = this.readInt();
		this._y = this.readInt();
		this._z = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			for (Shuttle shuttle : World.getInstance().getVisibleObjects(player, Shuttle.class))
			{
				if (shuttle.calculateDistance3D(player) < 1000.0)
				{
					shuttle.addPassenger(player);
					player.getInVehiclePosition().setXYZ(this._x, this._y, this._z);
					break;
				}

				PacketLogger.info(this.getClass().getSimpleName() + ": range between char and shuttle: " + shuttle.calculateDistance3D(player));
			}
		}
	}
}
