package org.l2jmobius.gameserver.model.events.holders.instance;

import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.instancezone.Instance;

public class OnInstanceDestroy implements IBaseEvent
{
	private final Instance _instance;

	public OnInstanceDestroy(Instance instance)
	{
		this._instance = instance;
	}

	public Instance getInstanceWorld()
	{
		return this._instance;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_INSTANCE_DESTROY;
	}
}
