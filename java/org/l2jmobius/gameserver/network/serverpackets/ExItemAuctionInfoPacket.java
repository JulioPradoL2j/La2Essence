package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.itemauction.ItemAuction;
import org.l2jmobius.gameserver.model.itemauction.ItemAuctionBid;
import org.l2jmobius.gameserver.model.itemauction.ItemAuctionState;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExItemAuctionInfoPacket extends AbstractItemPacket
{
	private final boolean _refresh;
	private final int _timeRemaining;
	private final ItemAuction _currentAuction;
	private final ItemAuction _nextAuction;

	public ExItemAuctionInfoPacket(boolean refresh, ItemAuction currentAuction, ItemAuction nextAuction)
	{
		if (currentAuction == null)
		{
			throw new NullPointerException();
		}
		if (currentAuction.getAuctionState() != ItemAuctionState.STARTED)
		{
			this._timeRemaining = 0;
		}
		else
		{
			this._timeRemaining = (int) (currentAuction.getFinishingTimeRemaining() / 1000L);
		}

		this._refresh = refresh;
		this._currentAuction = currentAuction;
		this._nextAuction = nextAuction;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ITEM_AUCTION_INFO.writeId(this, buffer);
		buffer.writeByte(!this._refresh);
		buffer.writeInt(this._currentAuction.getInstanceId());
		ItemAuctionBid highestBid = this._currentAuction.getHighestBid();
		buffer.writeLong(highestBid != null ? highestBid.getLastBid() : this._currentAuction.getAuctionInitBid());
		buffer.writeInt(this._timeRemaining);
		this.writeItem(this._currentAuction.getItemInfo(), buffer);
		if (this._nextAuction != null)
		{
			buffer.writeLong(this._nextAuction.getAuctionInitBid());
			buffer.writeInt((int) (this._nextAuction.getStartingTime() / 1000L));
			this.writeItem(this._nextAuction.getItemInfo(), buffer);
		}
	}
}
