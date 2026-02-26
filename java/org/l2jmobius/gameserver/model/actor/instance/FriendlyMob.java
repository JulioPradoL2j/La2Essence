package org.l2jmobius.gameserver.model.actor.instance;

import org.l2jmobius.gameserver.model.actor.Attackable;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.enums.creature.InstanceType;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;

public class FriendlyMob extends Attackable
{
	public FriendlyMob(NpcTemplate template)
	{
		super(template);
		this.setInstanceType(InstanceType.FriendlyMob);
	}

	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		return attacker.isPlayer() ? attacker.asPlayer().getReputation() < 0 : super.isAutoAttackable(attacker);
	}

	@Override
	public boolean isAggressive()
	{
		return true;
	}
}
