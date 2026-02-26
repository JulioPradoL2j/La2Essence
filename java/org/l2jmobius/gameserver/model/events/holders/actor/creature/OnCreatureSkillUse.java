package org.l2jmobius.gameserver.model.events.holders.actor.creature;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.skill.Skill;

public class OnCreatureSkillUse implements IBaseEvent
{
	private Creature _caster;
	private Skill _skill;
	private boolean _simultaneously;

	public Creature getCaster()
	{
		return this._caster;
	}

	public synchronized void setCaster(Creature caster)
	{
		this._caster = caster;
	}

	public Skill getSkill()
	{
		return this._skill;
	}

	public synchronized void setSkill(Skill skill)
	{
		this._skill = skill;
	}

	public boolean isSimultaneously()
	{
		return this._simultaneously;
	}

	public synchronized void setSimultaneously(boolean simultaneously)
	{
		this._simultaneously = simultaneously;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_CREATURE_SKILL_USE;
	}
}
