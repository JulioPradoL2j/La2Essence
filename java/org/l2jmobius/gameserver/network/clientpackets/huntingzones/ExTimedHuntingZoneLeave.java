package org.l2jmobius.gameserver.network.clientpackets.huntingzones;

import org.l2jmobius.commons.threads.ThreadPool;
import org.l2jmobius.gameserver.data.holders.TimedHuntingZoneHolder;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.TeleportWhereType;
import org.l2jmobius.gameserver.model.instancezone.Instance;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.network.serverpackets.huntingzones.TimedHuntingZoneExit;

public class ExTimedHuntingZoneLeave extends ClientPacket
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
			if (player.isInCombat())
			{
				player.sendPacket(new SystemMessage(SystemMessageId.YOU_CANNOT_TELEPORT_IN_BATTLE));
			}
			else
			{
				TimedHuntingZoneHolder huntingZone = player.getTimedHuntingZone();
				if (huntingZone != null)
				{
					Location exitLocation = huntingZone.getExitLocation();
					if (exitLocation != null)
					{
						player.teleToLocation(exitLocation, null);
					}
					else
					{
						Instance world = player.getInstanceWorld();
						if (world != null)
						{
							world.ejectPlayer(player);
						}
						else
						{
							player.teleToLocation(TeleportWhereType.TOWN);
						}
					}

					ThreadPool.schedule(() -> player.sendPacket(new TimedHuntingZoneExit(huntingZone.getZoneId())), 3000L);
				}
			}
		}
	}
}
