package org.l2jmobius.gameserver.network.serverpackets.blackcoupon;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.events.BlackCouponManager;
import org.l2jmobius.gameserver.model.item.enums.BlackCouponRestoreCategory;
import org.l2jmobius.gameserver.model.item.holders.ItemRestoreHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ItemRestoreList extends ServerPacket
{
	private final BlackCouponRestoreCategory _category;
	private final List<ItemRestoreHolder> _restoreItems;

	public ItemRestoreList()
	{
		this._category = BlackCouponRestoreCategory.WEAPON;
		this._restoreItems = BlackCouponManager.getInstance().getRestoreItems(0, this._category);
	}

	public ItemRestoreList(int playerObjectId)
	{
		this._category = BlackCouponRestoreCategory.WEAPON;
		this._restoreItems = BlackCouponManager.getInstance().getRestoreItems(playerObjectId, this._category);
	}

	public ItemRestoreList(int playerObjectId, BlackCouponRestoreCategory category)
	{
		this._category = category;
		this._restoreItems = BlackCouponManager.getInstance().getRestoreItems(playerObjectId, this._category);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ITEM_RESTORE_LIST.writeId(this, buffer);
		buffer.writeByte(this._category.ordinal());
		buffer.writeInt(this._restoreItems.size());

		for (ItemRestoreHolder holder : this._restoreItems)
		{
			buffer.writeInt(holder.getDestroyedItemId());
			buffer.writeInt(holder.getRepairItemId());
			buffer.writeByte(holder.getEnchantLevel());
			buffer.writeByte(this._restoreItems.lastIndexOf(holder));
		}
	}
}
