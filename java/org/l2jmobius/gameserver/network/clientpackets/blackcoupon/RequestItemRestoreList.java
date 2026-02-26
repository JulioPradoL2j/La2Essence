package org.l2jmobius.gameserver.network.clientpackets.blackcoupon;

import org.l2jmobius.gameserver.managers.events.BlackCouponManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.BlackCouponRestoreCategory;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.blackcoupon.ItemRestoreList;

public class RequestItemRestoreList extends ClientPacket
{
	private short _category;

	@Override
	public void readImpl()
	{
		this._category = this.readByte();
	}

	@Override
	public void runImpl()
	{
		if (BlackCouponManager.getInstance().getEventStatus())
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				BlackCouponRestoreCategory requestedCategory = BlackCouponRestoreCategory.getCategoryById(this._category);
				player.sendPacket(new ItemRestoreList(player.getObjectId(), requestedCategory));
			}
		}
	}
}
