package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerHasCastle extends Condition
{
	private final int _castle;

	public ConditionPlayerHasCastle(int castle)
	{
		this._castle = castle;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		if (!effector.isPlayer())
		{
			return false;
		}
		Clan clan = effector.asPlayer().getClan();
		if (clan == null)
		{
			return this._castle == 0;
		}
		return this._castle == -1 ? clan.getCastleId() > 0 : clan.getCastleId() == this._castle;
	}
}
