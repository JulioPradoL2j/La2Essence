package org.l2jmobius.gameserver.network.clientpackets.ranking;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ranking.ExOlympiadRankingInfo;

public class RequestOlympiadRankingInfo extends ClientPacket
{
	private int _tabId;
	private int _rankingType;
	private int _unk;
	private int _classId;
	private int _serverId;

	@Override
	protected void readImpl()
	{
		this._tabId = this.readByte();
		this._rankingType = this.readByte();
		this._unk = this.readByte();
		this._classId = this.readInt();
		this._serverId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExOlympiadRankingInfo(player, this._tabId, this._rankingType, this._unk, this._classId, this._serverId));
		}
	}
}
