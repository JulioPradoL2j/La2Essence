package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.data.holders.MultisellListHolder;
import org.l2jmobius.gameserver.data.xml.MultisellData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.PacketLogger;

public class RequestMultisellList extends ClientPacket
{
	private int _multiSellId;

	@Override
	protected void readImpl()
	{
		this._multiSellId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			MultisellListHolder multisell = MultisellData.getInstance().getMultisell(this._multiSellId);
			if (multisell == null)
			{
				PacketLogger.warning("RequestMultisellList: " + player + " requested non-existent list " + this._multiSellId + ".");
			}
			else
			{
				MultisellData.getInstance().separateAndSend(this._multiSellId, player, null, false, Double.NaN, Double.NaN, 4);
			}
		}
	}
}
