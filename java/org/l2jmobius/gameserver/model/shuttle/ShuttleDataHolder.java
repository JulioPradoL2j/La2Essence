package org.l2jmobius.gameserver.model.shuttle;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.StatSet;
import org.l2jmobius.gameserver.model.VehiclePathPoint;

public class ShuttleDataHolder
{
	private final int _id;
	private final Location _loc;
	private final List<Integer> _doors = new ArrayList<>(2);
	private final List<ShuttleStop> _stops = new ArrayList<>(2);
	private final List<VehiclePathPoint[]> _routes = new ArrayList<>(2);

	public ShuttleDataHolder(StatSet set)
	{
		this._id = set.getInt("id");
		this._loc = new Location(set);
	}

	public int getId()
	{
		return this._id;
	}

	public Location getLocation()
	{
		return this._loc;
	}

	public void addDoor(int id)
	{
		this._doors.add(id);
	}

	public List<Integer> getDoors()
	{
		return this._doors;
	}

	public void addStop(ShuttleStop stop)
	{
		this._stops.add(stop);
	}

	public List<ShuttleStop> getStops()
	{
		return this._stops;
	}

	public void addRoute(VehiclePathPoint[] route)
	{
		this._routes.add(route);
	}

	public List<VehiclePathPoint[]> getRoutes()
	{
		return this._routes;
	}
}
