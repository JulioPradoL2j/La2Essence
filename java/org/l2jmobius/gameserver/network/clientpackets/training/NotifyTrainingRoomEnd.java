package org.l2jmobius.gameserver.network.clientpackets.training;

import org.l2jmobius.gameserver.data.holders.TrainingHolder;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.training.ExTrainingZone_Leaving;

public class NotifyTrainingRoomEnd extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			TrainingHolder holder = player.getTraingCampInfo();
			if (holder != null)
			{
				if (holder.isTraining())
				{
					holder.setEndTime(System.currentTimeMillis());
					player.setTraingCampInfo(holder);
					player.enableAllSkills();
					player.setInvul(false);
					player.setInvisible(false);
					player.setImmobilized(false);
					player.teleToLocation(player.getLastLocation());
					player.sendPacket(ExTrainingZone_Leaving.STATIC_PACKET);
					holder.setEndTime(System.currentTimeMillis());
					player.setTraingCampInfo(holder);
				}
			}
		}
	}
}
