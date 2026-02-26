package org.l2jmobius.gameserver.model.events.holders.actor.npc;

import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnNpcMenuSelect implements IBaseEvent
{
	private final Player _player;
	private final Npc _npc;
	private final int _ask;
	private final int _reply;

	public OnNpcMenuSelect(Player player, Npc npc, int ask, int reply)
	{
		this._player = player;
		this._npc = npc;
		this._ask = ask;
		this._reply = reply;
	}

	public Player getTalker()
	{
		return this._player;
	}

	public Npc getNpc()
	{
		return this._npc;
	}

	public int getAsk()
	{
		return this._ask;
	}

	public int getReply()
	{
		return this._reply;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_NPC_MENU_SELECT;
	}
}
