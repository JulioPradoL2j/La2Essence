package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.ScriptManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.script.Quest;

public class RequestAddExpandQuestAlarm extends ClientPacket
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
			if (quest != null)
			{
				quest.sendNpcLogList(player);
			}
		}
	}
}
