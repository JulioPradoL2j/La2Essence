package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.StartRotation;

public class StartRotating extends ClientPacket
{
	private int _degree;
	private int _side;

	@Override
	protected void readImpl()
	{
		this._degree = this.readInt();
		this._side = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		if (PlayerConfig.ENABLE_KEYBOARD_MOVEMENT)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				if (player.isInAirShip() && player.getAirShip().isCaptain(player))
				{
					player.getAirShip().broadcastPacket(new StartRotation(player.getAirShip().getObjectId(), this._degree, this._side, 0));
				}
				else
				{
					player.broadcastPacket(new StartRotation(player.getObjectId(), this._degree, this._side, 0));
				}
			}
		}
	}
}
