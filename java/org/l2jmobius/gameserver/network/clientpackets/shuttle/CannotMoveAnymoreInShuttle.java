package org.l2jmobius.gameserver.network.clientpackets.shuttle;

import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.shuttle.ExStopMoveInShuttle;

public class CannotMoveAnymoreInShuttle extends ClientPacket
{
	private int _x;
	private int _y;
	private int _z;
	private int _heading;
	private int _boatId;

	@Override
	protected void readImpl()
	{
		this._boatId = this.readInt();
		this._x = this.readInt();
		this._y = this.readInt();
		this._z = this.readInt();
		this._heading = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (player.isInShuttle() && player.getShuttle().getObjectId() == this._boatId)
			{
				player.setInVehiclePosition(new Location(this._x, this._y, this._z));
				player.setHeading(this._heading);
				player.broadcastPacket(new ExStopMoveInShuttle(player, this._boatId));
			}
		}
	}
}
