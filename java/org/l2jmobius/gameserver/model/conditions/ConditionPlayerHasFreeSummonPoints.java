package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class ConditionPlayerHasFreeSummonPoints extends Condition
{
	private final int _summonPoints;

	public ConditionPlayerHasFreeSummonPoints(int summonPoints)
	{
		this._summonPoints = summonPoints;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		Player player = effector.asPlayer();
		if (player == null)
		{
			return false;
		}
		boolean canSummon = true;
		if (this._summonPoints == 0 && player.hasServitors())
		{
			player.sendPacket(SystemMessageId.YOU_CANNOT_USE_THE_S1_SKILL_DUE_TO_INSUFFICIENT_SUMMON_POINTS);
			canSummon = false;
		}
		else if (player.getSummonPoints() + this._summonPoints > player.getMaxSummonPoints())
		{
			SystemMessage sm = new SystemMessage(SystemMessageId.YOU_CANNOT_USE_THE_S1_SKILL_DUE_TO_INSUFFICIENT_SUMMON_POINTS);
			sm.addSkillName(skill);
			player.sendPacket(sm);
			canSummon = false;
		}

		return canSummon;
	}
}
