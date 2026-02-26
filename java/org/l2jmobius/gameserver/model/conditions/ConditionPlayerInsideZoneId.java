package org.l2jmobius.gameserver.model.conditions;

import java.util.Set;

import org.l2jmobius.gameserver.managers.ZoneManager;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.zone.ZoneType;

public class ConditionPlayerInsideZoneId extends Condition
{
	private final Set<Integer> _zones;

	public ConditionPlayerInsideZoneId(Set<Integer> zones)
	{
		this._zones = zones;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		if (effector.asPlayer() == null)
		{
			return false;
		}
		for (ZoneType zone : ZoneManager.getInstance().getZones(effector))
		{
			if (this._zones.contains(zone.getId()))
			{
				return true;
			}
		}

		return false;
	}
}
