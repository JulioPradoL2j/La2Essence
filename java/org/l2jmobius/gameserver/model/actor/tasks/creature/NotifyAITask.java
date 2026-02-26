package org.l2jmobius.gameserver.model.actor.tasks.creature;

import org.l2jmobius.gameserver.ai.Action;
import org.l2jmobius.gameserver.model.actor.Creature;

public class NotifyAITask implements Runnable
{
	private final Creature _creature;
	private final Action _action;

	public NotifyAITask(Creature creature, Action action)
	{
		this._creature = creature;
		this._action = action;
	}

	@Override
	public void run()
	{
		if (this._creature != null)
		{
			this._creature.getAI().notifyAction(this._action, null);
		}
	}
}
