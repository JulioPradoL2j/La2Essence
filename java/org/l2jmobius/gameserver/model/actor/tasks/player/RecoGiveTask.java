package org.l2jmobius.gameserver.model.actor.tasks.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class RecoGiveTask implements Runnable
{
	private final Player _player;

	public RecoGiveTask(Player player)
	{
		this._player = player;
	}

	@Override
	public void run()
	{
		if (this._player != null)
		{
			int recoToGive = 1;
			if (!this._player.isRecoTwoHoursGiven())
			{
				recoToGive = 10;
				this._player.setRecoTwoHoursGiven(true);
			}

			this._player.setRecomLeft(this._player.getRecomLeft() + recoToGive);
			SystemMessage sm = new SystemMessage(SystemMessageId.RECOMMENDATIONS_OBTAINED_S1);
			sm.addInt(recoToGive);
			this._player.sendPacket(sm);
			this._player.updateUserInfo();
		}
	}
}
