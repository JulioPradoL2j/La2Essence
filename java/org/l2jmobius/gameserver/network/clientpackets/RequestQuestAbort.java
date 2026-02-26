package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.ScriptManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.Containers;
import org.l2jmobius.gameserver.model.events.EventDispatcher;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.actor.player.OnPlayerQuestAbort;
import org.l2jmobius.gameserver.model.script.Quest;
import org.l2jmobius.gameserver.model.script.QuestState;
import org.l2jmobius.gameserver.model.script.QuestType;
import org.l2jmobius.gameserver.network.serverpackets.QuestList;

public class RequestQuestAbort extends ClientPacket
{
	private int _questId;

	@Override
	protected void readImpl()
	{
		this._questId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Quest qe = ScriptManager.getInstance().getQuest(this._questId);
			if (qe != null)
			{
				QuestState qs = player.getQuestState(qe.getName());
				if (qs != null)
				{
					qs.setSimulated(false);
					qs.exitQuest(QuestType.REPEATABLE);
					player.sendPacket(new QuestList(player));
					if (EventDispatcher.getInstance().hasListener(EventType.ON_PLAYER_QUEST_ABORT, player, Containers.Players()))
					{
						EventDispatcher.getInstance().notifyEventAsync(new OnPlayerQuestAbort(player, this._questId), player, Containers.Players());
					}

					qe.onQuestAborted(player);
				}
			}
		}
	}
}
