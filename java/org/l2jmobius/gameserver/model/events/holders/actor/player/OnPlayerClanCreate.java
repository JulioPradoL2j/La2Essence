package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerClanCreate implements IBaseEvent
{
	private final Player _player;
	private final Clan _clan;

	public OnPlayerClanCreate(Player player, Clan clan)
	{
		this._player = player;
		this._clan = clan;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public Clan getClan()
	{
		return this._clan;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_CLAN_CREATE;
	}
}
