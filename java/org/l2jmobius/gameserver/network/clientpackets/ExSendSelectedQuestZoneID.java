package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;

public class ExSendSelectedQuestZoneID extends ClientPacket
{
	private int _questZoneId;

	@Override
	protected void readImpl()
	{
		this._questZoneId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.setQuestZoneId(this._questZoneId);
		}
	}
}
