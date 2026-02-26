package org.l2jmobius.gameserver.network.clientpackets.teleports;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.data.holders.SharedTeleportHolder;
import org.l2jmobius.gameserver.managers.SharedTeleportManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.zone.ZoneId;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExRequestSharedLocationTeleport extends ClientPacket
{
	private int _id;

	@Override
	protected void readImpl()
	{
		this._id = (this.readInt() - 1) / 256;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			SharedTeleportHolder teleport = SharedTeleportManager.getInstance().getTeleport(this._id);
			if (teleport != null && teleport.getCount() != 0)
			{
				if (player.getName().equals(teleport.getName()))
				{
					player.sendPacket(SystemMessageId.YOU_CANNOT_TELEPORT_TO_YOURSELF);
				}
				else if (player.getInventory().getInventoryItemCount(91663, -1) < GeneralConfig.TELEPORT_SHARE_LOCATION_COST)
				{
					player.sendPacket(SystemMessageId.THERE_ARE_NOT_ENOUGH_L_COINS);
				}
				else if (player.getMovieHolder() == null && !player.isFishing() && !player.isInInstance() && !player.isOnEvent() && !player.isInOlympiadMode() && !player.inObserverMode() && !player.isInTraingCamp() && !player.isInTimedHuntingZone() && !player.isInsideZone(ZoneId.SIEGE))
				{
					if (player.destroyItemByItemId(ItemProcessType.FEE, 91663, GeneralConfig.TELEPORT_SHARE_LOCATION_COST, player, true))
					{
						teleport.decrementCount();
						player.abortCast();
						player.stopMove(null);
						player.teleToLocation(teleport.getLocation());
					}
				}
				else
				{
					player.sendPacket(SystemMessageId.YOU_CANNOT_TELEPORT_RIGHT_NOW);
				}
			}
			else
			{
				player.sendPacket(SystemMessageId.TELEPORTATION_LIMIT_FOR_THE_COORDINATES_RECEIVED_IS_REACHED);
			}
		}
	}
}
