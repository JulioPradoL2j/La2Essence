package org.l2jmobius.gameserver.network.clientpackets.quest;

import org.l2jmobius.gameserver.managers.ScriptManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.Containers;
import org.l2jmobius.gameserver.model.events.EventDispatcher;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.actor.player.OnPlayerQuestAbort;
import org.l2jmobius.gameserver.model.script.Quest;
import org.l2jmobius.gameserver.model.script.QuestState;
import org.l2jmobius.gameserver.model.script.QuestType;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.quest.ExQuestNotificationAll;
import org.l2jmobius.gameserver.network.serverpackets.quest.ExQuestUI;

public class RequestExQuestCancel extends ClientPacket
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
			Quest quest = ScriptManager.getInstance().getQuest(this._questId);
			QuestState qs = quest.getQuestState(player, false);
			if (qs != null && !qs.isCompleted())
			{
				qs.setSimulated(false);
				qs.exitQuest(QuestType.REPEATABLE);
				player.sendPacket(new ExQuestUI(player));
				player.sendPacket(new ExQuestNotificationAll(player));
				if (EventDispatcher.getInstance().hasListener(EventType.ON_PLAYER_QUEST_ABORT, player, Containers.Players()))
				{
					EventDispatcher.getInstance().notifyEventAsync(new OnPlayerQuestAbort(player, this._questId), player, Containers.Players());
				}

				quest.onQuestAborted(player);
			}
		}
	}
}
