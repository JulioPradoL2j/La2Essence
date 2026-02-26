package org.l2jmobius.gameserver.model.actor.instance;

import org.l2jmobius.gameserver.data.enums.CategoryType;
import org.l2jmobius.gameserver.data.xml.CategoryData;
import org.l2jmobius.gameserver.model.actor.enums.creature.Race;
import org.l2jmobius.gameserver.model.actor.enums.player.PlayerClass;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;

public class VillageMasterMystic extends VillageMaster
{
	public VillageMasterMystic(NpcTemplate template)
	{
		super(template);
	}

	@Override
	protected final boolean checkVillageMasterRace(PlayerClass pClass)
	{
		return pClass == null ? false : pClass.getRace() == Race.HUMAN || pClass.getRace() == Race.ELF;
	}

	@Override
	protected final boolean checkVillageMasterTeachType(PlayerClass pClass)
	{
		return pClass == null ? false : CategoryData.getInstance().isInCategory(CategoryType.MAGE_GROUP, pClass.getId());
	}
}
