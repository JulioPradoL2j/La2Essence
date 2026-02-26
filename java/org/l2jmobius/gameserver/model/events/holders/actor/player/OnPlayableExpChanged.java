package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Playable;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayableExpChanged implements IBaseEvent
{
	private final Playable _playable;
	private final long _oldExp;
	private final long _newExp;

	public OnPlayableExpChanged(Playable playable, long oldExp, long newExp)
	{
		this._playable = playable;
		this._oldExp = oldExp;
		this._newExp = newExp;
	}

	public Playable getPlayable()
	{
		return this._playable;
	}

	public long getOldExp()
	{
		return this._oldExp;
	}

	public long getNewExp()
	{
		return this._newExp;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYABLE_EXP_CHANGED;
	}
}
