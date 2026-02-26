package org.l2jmobius.gameserver.network.clientpackets.castlewar;

import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExCastleWarObserverStart extends ClientPacket
{
	private int _castleId;

	@Override
	protected void readImpl()
	{
		this._castleId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (player.hasSummon())
			{
				player.sendPacket(SystemMessageId.YOU_MAY_NOT_OBSERVE_A_SIEGE_WITH_A_SERVITOR_SUMMONED);
			}
			else if (player.isOnEvent())
			{
				player.sendMessage("Cannot use while on an event.");
			}
			else
			{
				Castle castle = CastleManager.getInstance().getCastleById(this._castleId);
				if (castle != null)
				{
					if (castle.getSiege().isInProgress())
					{
						Player random = castle.getSiege().getPlayersInZone().stream().findAny().orElse(null);
						if (random != null)
						{
							player.enterObserverMode(random.getLocation());
						}
					}
				}
			}
		}
	}
}
