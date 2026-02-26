package org.l2jmobius.gameserver.network.clientpackets.sayune;

import org.l2jmobius.gameserver.config.custom.SayuneForAllConfig;
import org.l2jmobius.gameserver.data.xml.SayuneData;
import org.l2jmobius.gameserver.managers.ZoneManager;
import org.l2jmobius.gameserver.model.SayuneEntry;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.SayuneRequest;
import org.l2jmobius.gameserver.model.zone.ZoneId;
import org.l2jmobius.gameserver.model.zone.type.SayuneZone;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestFlyMoveStart extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && player.isInsideZone(ZoneId.SAYUNE) && !player.hasRequest(SayuneRequest.class) && SayuneForAllConfig.FREE_JUMPS_FOR_ALL)
		{
			if (player.hasSummon())
			{
				player.sendPacket(SystemMessageId.YOU_MAY_NOT_USE_SAYUNE_WHILE_A_SERVITOR_IS_AROUND);
			}
			else if (player.getReputation() < 0)
			{
				player.sendPacket(SystemMessageId.YOU_CANNOT_USE_SAYUNE_WHILE_IN_A_CHAOTIC_STATE);
			}
			else if (player.hasRequests())
			{
				player.sendPacket(SystemMessageId.SAYUNE_CANNOT_BE_USED_WHILE_TAKING_OTHER_ACTIONS);
			}
			else
			{
				SayuneZone zone = ZoneManager.getInstance().getZone(player, SayuneZone.class);
				if (zone.getMapId() == -1)
				{
					player.sendMessage("That zone is not supported yet!");
					PacketLogger.warning(this.getClass().getSimpleName() + ": " + player + " Requested sayune on zone with no map id set");
				}
				else
				{
					SayuneEntry map = SayuneData.getInstance().getMap(zone.getMapId());
					if (map == null)
					{
						player.sendMessage("This zone is not handled yet!!");
						PacketLogger.warning(this.getClass().getSimpleName() + ": " + player + " Requested sayune on unhandled map zone " + zone.getName());
					}
					else
					{
						SayuneRequest request = new SayuneRequest(player, map.getId());
						if (player.addRequest(request))
						{
							request.move(player, 0);
						}
					}
				}
			}
		}
	}
}
