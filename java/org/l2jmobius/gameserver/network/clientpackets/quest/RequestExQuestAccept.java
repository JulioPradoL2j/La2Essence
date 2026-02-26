package org.l2jmobius.gameserver.network.clientpackets.quest;

import org.l2jmobius.gameserver.managers.ScriptManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.script.Quest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestExQuestAccept extends ClientPacket
{
	private int _questId;
	private boolean _isAccepted;

	@Override
	protected void readImpl()
	{
		this._questId = this.readInt();
		this._isAccepted = this.readBoolean();
	}

	@Override
	protected void runImpl()
	{
		if (this._isAccepted)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				Quest quest = ScriptManager.getInstance().getQuest(this._questId);
				if (quest != null)
				{
					quest.notifyEvent("ACCEPT", null, player);
				}
			}
		}
	}
}
