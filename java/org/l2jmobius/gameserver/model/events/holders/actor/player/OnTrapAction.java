package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.enums.creature.TrapAction;
import org.l2jmobius.gameserver.model.actor.instance.Trap;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnTrapAction implements IBaseEvent
{
	private final Trap _trap;
	private final Creature _trigger;
	private final TrapAction _action;

	public OnTrapAction(Trap trap, Creature trigger, TrapAction action)
	{
		this._trap = trap;
		this._trigger = trigger;
		this._action = action;
	}

	public Trap getTrap()
	{
		return this._trap;
	}

	public Creature getTrigger()
	{
		return this._trigger;
	}

	public TrapAction getAction()
	{
		return this._action;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_TRAP_ACTION;
	}
}
