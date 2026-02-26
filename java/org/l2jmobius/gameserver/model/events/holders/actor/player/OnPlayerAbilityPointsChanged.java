package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerAbilityPointsChanged implements IBaseEvent
{
	private final Player _player;
	private final int _newAbilityPoints;
	private final int _oldAbilityPoints;

	public OnPlayerAbilityPointsChanged(Player player, int newAbilityPoints, int oldAbilityPoints)
	{
		this._player = player;
		this._newAbilityPoints = newAbilityPoints;
		this._oldAbilityPoints = oldAbilityPoints;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public long getNewAbilityPoints()
	{
		return this._newAbilityPoints;
	}

	public long getOldAbilityPoints()
	{
		return this._oldAbilityPoints;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_ABILITY_POINTS_CHANGED;
	}
}
