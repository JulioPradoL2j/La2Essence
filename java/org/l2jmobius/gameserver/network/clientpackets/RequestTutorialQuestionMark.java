package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventDispatcher;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.actor.player.OnPlayerPressTutorialMark;

public class RequestTutorialQuestionMark extends ClientPacket
{
	private int _number = 0;

	@Override
	protected void readImpl()
	{
		this.readByte();
		this._number = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (EventDispatcher.getInstance().hasListener(EventType.ON_PLAYER_PRESS_TUTORIAL_MARK, player))
			{
				EventDispatcher.getInstance().notifyEventAsync(new OnPlayerPressTutorialMark(player, this._number), player);
			}
		}
	}
}
