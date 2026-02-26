package org.l2jmobius.gameserver.network.clientpackets.newhenna;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.henna.Henna;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import org.l2jmobius.gameserver.network.serverpackets.HennaItemRemoveInfo;

public class RequestNewHennaUnequipInfo extends ClientPacket
{
	private int _hennaId;

	@Override
	protected void readImpl()
	{
		this._hennaId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && this._hennaId != 0)
		{
			Henna henna = null;

			for (int slot = 1; slot <= 4; slot++)
			{
				Henna equipedHenna = player.getHenna(slot);
				if (equipedHenna != null && equipedHenna.getDyeId() == this._hennaId)
				{
					henna = equipedHenna;
					break;
				}
			}

			if (henna == null)
			{
				PacketLogger.warning("Invalid Henna Id: " + this._hennaId + " from " + player);
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else
			{
				player.sendPacket(new HennaItemRemoveInfo(henna, player));
			}
		}
	}
}
