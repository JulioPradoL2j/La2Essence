package org.l2jmobius.gameserver.network.clientpackets.commission;

import org.l2jmobius.gameserver.managers.ItemCommissionManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.commission.ExCloseCommission;

public class RequestCommissionBuyItem extends ClientPacket
{
	private long _commissionId;

	@Override
	protected void readImpl()
	{
		this._commissionId = this.readLong();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (!ItemCommissionManager.isPlayerAllowedToInteract(player))
			{
				player.sendPacket(ExCloseCommission.STATIC_PACKET);
			}
			else
			{
				ItemCommissionManager.getInstance().buyItem(player, this._commissionId);
			}
		}
	}
}
