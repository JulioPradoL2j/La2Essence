package org.l2jmobius.gameserver.network.clientpackets.ranking;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ranking.ExRankingCharRankers;

public class RequestRankingCharRankers extends ClientPacket
{
	private int _group;
	private int _scope;
	private int _ordinal;
	private int _baseclass;

	@Override
	protected void readImpl()
	{
		this._group = this.readByte();
		this._scope = this.readByte();
		this._ordinal = this.readInt();
		this._baseclass = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExRankingCharRankers(player, this._group, this._scope, this._ordinal, this._baseclass));
		}
	}
}
