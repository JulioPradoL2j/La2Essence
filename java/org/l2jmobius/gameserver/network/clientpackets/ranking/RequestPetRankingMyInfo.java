package org.l2jmobius.gameserver.network.clientpackets.ranking;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ranking.ExPetRankingMyInfo;

public class RequestPetRankingMyInfo extends ClientPacket
{
	private int _petId;

	@Override
	protected void readImpl()
	{
		this._petId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExPetRankingMyInfo(player, this._petId));
		}
	}
}
