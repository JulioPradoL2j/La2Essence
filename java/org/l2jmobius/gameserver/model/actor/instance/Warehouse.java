package org.l2jmobius.gameserver.model.actor.instance;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.creature.InstanceType;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;

public class Warehouse extends Folk
{
	public Warehouse(NpcTemplate template)
	{
		super(template);
		this.setInstanceType(InstanceType.Warehouse);
	}

	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		return attacker.isMonster() ? true : super.isAutoAttackable(attacker);
	}

	@Override
	public boolean isWarehouse()
	{
		return true;
	}

	@Override
	public String getHtmlPath(int npcId, int value, Player player)
	{
		String pom = "";
		if (value == 0)
		{
			pom = Integer.toString(npcId);
		}
		else
		{
			pom = npcId + "-" + value;
		}

		return "data/html/warehouse/" + pom + ".htm";
	}
}
