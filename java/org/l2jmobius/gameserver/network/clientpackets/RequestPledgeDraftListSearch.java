package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.ClanEntryManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExPledgeDraftListSearch;
import org.l2jmobius.gameserver.util.MathUtil;

public class RequestPledgeDraftListSearch extends ClientPacket
{
	private int _levelMin;
	private int _levelMax;
	private int _classId;
	private String _query;
	private int _sortBy;
	private boolean _descending;

	@Override
	protected void readImpl()
	{
		this._levelMin = MathUtil.clamp(this.readInt(), 0, 107);
		this._levelMax = MathUtil.clamp(this.readInt(), 0, 107);
		this._classId = this.readInt();
		this._query = this.readString();
		this._sortBy = this.readInt();
		this._descending = this.readInt() == 2;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._query.isEmpty())
			{
				player.sendPacket(new ExPledgeDraftListSearch(ClanEntryManager.getInstance().getSortedWaitingList(this._levelMin, this._levelMax, this._classId, this._sortBy, this._descending)));
			}
			else
			{
				player.sendPacket(new ExPledgeDraftListSearch(ClanEntryManager.getInstance().queryWaitingListByName(this._query.toLowerCase())));
			}
		}
	}
}
