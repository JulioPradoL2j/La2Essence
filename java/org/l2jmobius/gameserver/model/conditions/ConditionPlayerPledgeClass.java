package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerPledgeClass extends Condition
{
	private final int _pledgeClass;

	public ConditionPlayerPledgeClass(int pledgeClass)
	{
		this._pledgeClass = pledgeClass;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		Player player = effector.asPlayer();
		if (player != null && player.getClan() != null)
		{
			boolean isClanLeader = player.isClanLeader();
			return this._pledgeClass == -1 && !isClanLeader ? false : isClanLeader || player.getPledgeClass() >= this._pledgeClass;
		}
		return false;
	}
}
