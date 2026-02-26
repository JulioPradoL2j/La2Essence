package org.l2jmobius.gameserver.model.zone.type;

import java.util.EnumMap;
import java.util.Map;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.creature.Race;
import org.l2jmobius.gameserver.model.zone.ZoneRespawn;

public class RespawnZone extends ZoneRespawn
{
	private final Map<Race, String> _raceRespawnPoint = new EnumMap<>(Race.class);

	public RespawnZone(int id)
	{
		super(id);
	}

	@Override
	protected void onEnter(Creature creature)
	{
	}

	@Override
	protected void onExit(Creature creature)
	{
	}

	public void addRaceRespawnPoint(String race, String point)
	{
		this._raceRespawnPoint.put(Race.valueOf(race), point);
	}

	public Map<Race, String> getAllRespawnPoints()
	{
		return this._raceRespawnPoint;
	}

	public String getRespawnPoint(Player player)
	{
		return this._raceRespawnPoint.get(player.getRace());
	}
}
