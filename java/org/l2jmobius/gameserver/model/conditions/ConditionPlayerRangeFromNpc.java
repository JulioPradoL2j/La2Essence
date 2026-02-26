package org.l2jmobius.gameserver.model.conditions;

import java.util.Set;

import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;

public class ConditionPlayerRangeFromNpc extends Condition
{
	private final Set<Integer> _npcIds;
	private final int _radius;
	private final boolean _value;

	public ConditionPlayerRangeFromNpc(Set<Integer> npcIds, int radius, boolean value)
	{
		this._npcIds = npcIds;
		this._radius = radius;
		this._value = value;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		boolean existNpc = false;
		if (!this._npcIds.isEmpty() && this._radius > 0)
		{
			for (Npc target : World.getInstance().getVisibleObjectsInRange(effector, Npc.class, this._radius))
			{
				if (this._npcIds.contains(target.getId()))
				{
					existNpc = true;
					break;
				}
			}
		}

		return existNpc == this._value;
	}
}
