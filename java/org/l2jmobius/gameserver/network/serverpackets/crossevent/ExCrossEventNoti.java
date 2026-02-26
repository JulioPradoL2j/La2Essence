package org.l2jmobius.gameserver.network.serverpackets.crossevent;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.events.CrossEventManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCrossEventNoti extends ServerPacket
{
	private final int _coupons;
	private final int _advancedRewards;

	public ExCrossEventNoti(Player player)
	{
		this._coupons = (int) player.getInventory().getItemByItemId(CrossEventManager.getInstance().getTicketId()).getCount();
		this._advancedRewards = player.getCrossRewardsCount();
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CROSS_EVENT_NOTI.writeId(this, buffer);
		buffer.writeInt(this._coupons);
		buffer.writeInt(this._advancedRewards);
	}
}
