package org.l2jmobius.gameserver.model.actor.instance;

import java.util.Iterator;
import java.util.List;

import org.l2jmobius.gameserver.ai.ShuttleAI;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.Vehicle;
import org.l2jmobius.gameserver.model.actor.enums.creature.InstanceType;
import org.l2jmobius.gameserver.model.actor.templates.CreatureTemplate;
import org.l2jmobius.gameserver.model.shuttle.ShuttleDataHolder;
import org.l2jmobius.gameserver.model.shuttle.ShuttleStop;
import org.l2jmobius.gameserver.network.serverpackets.shuttle.ExShuttleGetOff;
import org.l2jmobius.gameserver.network.serverpackets.shuttle.ExShuttleGetOn;
import org.l2jmobius.gameserver.network.serverpackets.shuttle.ExShuttleInfo;

public class Shuttle extends Vehicle
{
	private ShuttleDataHolder _shuttleData;

	public Shuttle(CreatureTemplate template)
	{
		super(template);
		this.setInstanceType(InstanceType.Shuttle);
		this.setAI(new ShuttleAI(this));
	}

	public List<ShuttleStop> getStops()
	{
		return this._shuttleData.getStops();
	}

	public void closeDoor(int id)
	{
		for (ShuttleStop stop : this._shuttleData.getStops())
		{
			if (stop.getId() == id)
			{
				stop.closeDoor();
				break;
			}
		}
	}

	public void openDoor(int id)
	{
		for (ShuttleStop stop : this._shuttleData.getStops())
		{
			if (stop.getId() == id)
			{
				stop.openDoor();
				break;
			}
		}
	}

	@Override
	public int getId()
	{
		return this._shuttleData.getId();
	}

	@Override
	public boolean addPassenger(Player player)
	{
		if (!super.addPassenger(player))
		{
			return false;
		}
		player.setVehicle(this);
		player.setInVehiclePosition(new Location(0, 0, 0));
		player.broadcastPacket(new ExShuttleGetOn(player, this));
		player.setXYZ(this.getX(), this.getY(), this.getZ());
		player.revalidateZone(true);
		return true;
	}

	public void removePassenger(Player player, int x, int y, int z)
	{
		this.oustPlayer(player);
		if (player.isOnline())
		{
			player.broadcastPacket(new ExShuttleGetOff(player, this, x, y, z));
			player.setXYZ(x, y, z);
			player.revalidateZone(true);
		}
		else
		{
			player.setXYZInvisible(x, y, z);
		}
	}

	@Override
	public void oustPlayers()
	{
		Iterator<Player> iter = this._passengers.iterator();

		while (iter.hasNext())
		{
			Player player = iter.next();
			iter.remove();
			if (player != null)
			{
				this.oustPlayer(player);
			}
		}
	}

	@Override
	public void sendInfo(Player player)
	{
		player.sendPacket(new ExShuttleInfo(this));
	}

	public void broadcastShuttleInfo()
	{
		this.broadcastPacket(new ExShuttleInfo(this));
	}

	public void setData(ShuttleDataHolder data)
	{
		this._shuttleData = data;
	}

	public ShuttleDataHolder getShuttleData()
	{
		return this._shuttleData;
	}
}
