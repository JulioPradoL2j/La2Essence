package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.item.henna.Henna;

public class OnPlayerHennaAdd implements IBaseEvent
{
	private final Player _player;
	private final Henna _henna;

	public OnPlayerHennaAdd(Player player, Henna henna)
	{
		this._player = player;
		this._henna = henna;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public Henna getHenna()
	{
		return this._henna;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_HENNA_ADD;
	}
}
