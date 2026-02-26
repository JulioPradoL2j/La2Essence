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
import org.l2jmobius.gameserver.network.ConnectionState;
import org.l2jmobius.gameserver.network.Disconnection;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import org.l2jmobius.gameserver.network.serverpackets.CharSelectionInfo;
import org.l2jmobius.gameserver.network.serverpackets.RestartResponse;

public class RequestRestart extends ClientPacket
{
	protected static final Logger LOGGER_ACCOUNTING = Logger.getLogger("accounting");

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
			if (!player.canLogout())
			{
				player.sendPacket(RestartResponse.FALSE);
				player.sendPacket(ActionFailed.STATIC_PACKET);
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

				GameClient client = this.getClient();
				if (OfflineTraderTable.getInstance().enteredOfflineMode(player))
				{
					LOGGER_ACCOUNTING.info("Entered offline mode, " + client);
				}
				else
				{
					Disconnection.of(client, player).storeAndDelete();
				}

				client.setConnectionState(ConnectionState.AUTHENTICATED);
				client.sendPacket(RestartResponse.TRUE);
				CharSelectionInfo cl = new CharSelectionInfo(client.getAccountName(), client.getSessionId().playOkID1);
				client.sendPacket(new CharSelectionInfo(client.getAccountName(), client.getSessionId().playOkID1));
				client.setCharSelection(cl.getCharInfo());
			}
		}
	}
}
