package org.l2jmobius.gameserver.network.clientpackets.quest;

import org.l2jmobius.gameserver.managers.ScriptManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.script.Quest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestExQuestComplete extends ClientPacket
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
		if (player != null && !player.isTeleporting() && !player.isCastingTeleportSkill())
		{
			Quest quest = ScriptManager.getInstance().getQuest(this._questId);
			if (quest != null)
			{
				quest.notifyEvent("COMPLETE", null, player);
			}
		}
	}
}
