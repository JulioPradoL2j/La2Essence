package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.Party;

public class AnswerPartyLootModification extends ClientPacket
{
	public int _answer;

	@Override
	protected void readImpl()
	{
		this._answer = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Party party = player.getParty();
			if (party != null)
			{
				party.answerLootChangeRequest(player, this._answer == 1);
			}
		}
	}
}
