package org.l2jmobius.gameserver.model.events.listeners;

import java.util.function.Consumer;

import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.ListenersContainer;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.events.returns.AbstractEventReturn;

public class ConsumerEventListener extends AbstractEventListener
{
	private final Consumer<Object> _callback;

	@SuppressWarnings("unchecked")
	public ConsumerEventListener(ListenersContainer container, EventType type, Consumer<?> callback, Object owner)
	{
		super(container, type, owner);
		this._callback = (Consumer<Object>) callback;
	}

	@Override
	public <R extends AbstractEventReturn> R executeEvent(IBaseEvent event, Class<R> returnBackClass)
	{
		this._callback.accept(event);
		return null;
	}
}
