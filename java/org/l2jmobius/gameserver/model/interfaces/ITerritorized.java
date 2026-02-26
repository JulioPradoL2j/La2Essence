package org.l2jmobius.gameserver.model.interfaces;

import java.util.List;

import org.l2jmobius.gameserver.model.zone.type.BannedSpawnTerritory;
import org.l2jmobius.gameserver.model.zone.type.SpawnTerritory;

public interface ITerritorized
{
	void addTerritory(SpawnTerritory var1);

	List<SpawnTerritory> getTerritories();

	void addBannedTerritory(BannedSpawnTerritory var1);

	List<BannedSpawnTerritory> getBannedTerritories();
}
