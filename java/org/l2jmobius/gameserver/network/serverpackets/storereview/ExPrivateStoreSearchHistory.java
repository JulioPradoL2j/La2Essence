package org.l2jmobius.gameserver.network.serverpackets.storereview;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.PrivateStoreHistoryManager;
import org.l2jmobius.gameserver.managers.PrivateStoreHistoryManager.ItemHistoryTransaction;
import org.l2jmobius.gameserver.model.actor.enums.player.PrivateStoreType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.AbstractItemPacket;

public class ExPrivateStoreSearchHistory extends AbstractItemPacket
{
	private final int _page;
	private final int _maxPage;
	private final List<PrivateStoreHistoryManager.ItemHistoryTransaction> _history;

	public ExPrivateStoreSearchHistory(int page, int maxPage, List<PrivateStoreHistoryManager.ItemHistoryTransaction> history)
	{
		this._page = page;
		this._maxPage = maxPage;
		this._history = history;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PRIVATE_STORE_SEARCH_HISTORY.writeId(this, buffer);
		buffer.writeByte(this._page);
		buffer.writeByte(this._maxPage);
		buffer.writeInt(this._history.size());

		for (ItemHistoryTransaction transaction : this._history)
		{
			buffer.writeInt(transaction.getItemId());
			buffer.writeByte(transaction.getTransactionType() == PrivateStoreType.SELL ? 0 : 1);
			buffer.writeByte(transaction.getEnchantLevel());
			buffer.writeLong(transaction.getPrice() / transaction.getCount());
			buffer.writeLong(transaction.getCount());
		}
	}
}
