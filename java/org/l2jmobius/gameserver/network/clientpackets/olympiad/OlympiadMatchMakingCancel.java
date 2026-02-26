package org.l2jmobius.gameserver.network.clientpackets.olympiad;

import org.l2jmobius.gameserver.config.OlympiadConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.olympiad.Olympiad;
import org.l2jmobius.gameserver.model.olympiad.OlympiadManager;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.olympiad.ExOlympiadInfo;
import org.l2jmobius.gameserver.network.serverpackets.olympiad.ExOlympiadMatchMakingResult;
import org.l2jmobius.gameserver.network.serverpackets.olympiad.ExOlympiadRecord;

public class OlympiadMatchMakingCancel extends ClientPacket
{
	private byte _cGameRuleType;

	@Override
	protected void readImpl()
	{
		this._cGameRuleType = this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._cGameRuleType == 1)
			{
				OlympiadManager.getInstance().unRegisterNoble(player);
			}

			player.sendPacket(new ExOlympiadMatchMakingResult(this._cGameRuleType, 0));
			if (OlympiadConfig.OLYMPIAD_ENABLED && Olympiad.getInstance().inCompPeriod())
			{
				player.sendPacket(new ExOlympiadInfo(1, Olympiad.getInstance().getRemainingTime()));
			}
			else
			{
				player.sendPacket(new ExOlympiadInfo(0, Olympiad.getInstance().getRemainingTime()));
			}

			player.sendPacket(new ExOlympiadRecord(player, this._cGameRuleType));
		}
	}
}
