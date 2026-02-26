package org.l2jmobius.gameserver.network.clientpackets.olympiad;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.olympiad.ExOlympiadRecord;

public class OlympiadUI extends ClientPacket
{
	private byte _gameRuleType;

	@Override
	protected void readImpl()
	{
		this._gameRuleType = 1;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExOlympiadRecord(player, this._gameRuleType));
		}
	}
}
