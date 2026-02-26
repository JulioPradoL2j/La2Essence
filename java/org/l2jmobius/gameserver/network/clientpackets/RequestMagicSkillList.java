package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.PacketLogger;

public class RequestMagicSkillList extends ClientPacket
{
	private int _objectId;

	@Override
	protected void readImpl()
	{
		this._objectId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (player.getObjectId() != this._objectId)
			{
				PacketLogger.warning("Player: " + player + " requested " + this.getClass().getSimpleName() + " with different object id: " + this._objectId);
			}
			else
			{
				player.sendSkillList();
			}
		}
	}
}
