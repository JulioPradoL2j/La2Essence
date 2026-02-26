package org.l2jmobius.gameserver.network.clientpackets.dualinventory;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestExDualInventorySwap extends ClientPacket
{
	private int _slot;

	@Override
	protected void readImpl()
	{
		this._slot = this.readByte() == 0 ? 0 : 1;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.setDualInventorySlot(this._slot);
		}
	}
}
