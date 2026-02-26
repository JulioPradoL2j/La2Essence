package org.l2jmobius.gameserver.model.script.timers;

@FunctionalInterface
public interface IEventTimerCancel<T>
{
	void onTimerCancel(TimerHolder<T> var1);
}
