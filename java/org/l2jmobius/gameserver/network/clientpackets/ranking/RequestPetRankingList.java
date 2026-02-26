package org.l2jmobius.gameserver.network.clientpackets.ranking;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ranking.ExPetRankingList;

public class RequestPetRankingList extends ClientPacket
{
	private int _season;
	private int _tabId;
	private int _type;
	private int _petItemObjectId;

	@Override
	protected void readImpl()
	{
		this._season = this.readByte();
		this._tabId = this.readByte();
		this._type = this.readByte();
		this._petItemObjectId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExPetRankingList(player, this._season, this._tabId, this._type, this._petItemObjectId));
		}
	}
}
