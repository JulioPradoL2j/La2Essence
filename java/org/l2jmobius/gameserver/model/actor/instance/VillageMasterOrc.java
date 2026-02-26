package org.l2jmobius.gameserver.model.actor.instance;

import org.l2jmobius.gameserver.model.actor.enums.creature.Race;
import org.l2jmobius.gameserver.model.actor.enums.player.PlayerClass;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;

public class VillageMasterOrc extends VillageMaster
{
	public VillageMasterOrc(NpcTemplate template)
	{
		super(template);
	}

	@Override
	protected final boolean checkVillageMasterRace(PlayerClass pClass)
	{
		return pClass == null ? false : pClass.getRace() == Race.ORC;
	}
}
