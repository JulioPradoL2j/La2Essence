package org.l2jmobius.gameserver.model.skill;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.l2jmobius.gameserver.model.item.holders.ItemSkillHolder;

public class AmmunitionSkillList
{
	private static final Set<Integer> SKILLS = ConcurrentHashMap.newKeySet();

	public static void add(List<ItemSkillHolder> skills)
	{
		for (ItemSkillHolder skill : skills)
		{
			SKILLS.add(skill.getSkillId());
		}
	}

	public static Set<Integer> values()
	{
		return SKILLS;
	}
}
