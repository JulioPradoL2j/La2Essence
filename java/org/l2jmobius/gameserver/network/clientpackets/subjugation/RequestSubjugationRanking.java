package org.l2jmobius.gameserver.network.clientpackets.subjugation;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.subjugation.ExSubjugationRanking;

public class RequestSubjugationRanking extends ClientPacket
{
	private int _rankingCategory;

	@Override
	protected void readImpl()
	{
		this._rankingCategory = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExSubjugationRanking(this._rankingCategory, player.getObjectId()));
		}
	}
}
