package org.l2jmobius.gameserver.network.clientpackets;

import java.util.logging.Logger;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.data.sql.OfflineTraderTable;
import org.l2jmobius.gameserver.managers.MapRegionManager;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.TeleportWhereType;
import org.l2jmobius.gameserver.model.instancezone.Instance;
import org.l2jmobius.gameserver.model.olympiad.OlympiadManager;
import org.l2jmobius.gameserver.network.Disconnection;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.serverpackets.LeaveWorld;

public class Logout extends ClientPacket
{
	protected static final Logger LOGGER_ACCOUNTING = Logger.getLogger("accounting");

	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		GameClient client = this.getClient();
		Player player = client.getPlayer();
		if (player == null)
		{
			client.disconnect();
		}
		else
		{
			if (OlympiadManager.getInstance().isRegistered(player))
			{
				OlympiadManager.getInstance().unRegisterNoble(player);
			}

			Location location = null;
			Instance world = player.getInstanceWorld();
			if (world != null)
			{
				if (GeneralConfig.RESTORE_PLAYER_INSTANCE)
				{
					player.getVariables().set("INSTANCE_RESTORE", world.getId());
				}
				else
				{
					location = world.getExitLocation(player);
					if (location == null)
					{
						location = MapRegionManager.getInstance().getTeleToLocation(player, TeleportWhereType.TOWN);
					}
				}

				player.setInstance(null);
			}
			else if (player.isInTimedHuntingZone())
			{
				location = MapRegionManager.getInstance().getTeleToLocation(player, TeleportWhereType.TOWN);
			}

			if (location != null)
			{
				player.getVariables().set("RESTORE_LOCATION", location.getX() + ";" + location.getY() + ";" + location.getZ());
			}

			if (OfflineTraderTable.getInstance().enteredOfflineMode(player))
			{
				LOGGER_ACCOUNTING.info("Entered offline mode, " + client);
			}
			else
			{
				Disconnection.of(client, player).storeAndDeleteWith(LeaveWorld.STATIC_PACKET);
			}
		}
	}
}
