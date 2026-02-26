package org.l2jmobius.gameserver.network.clientpackets.newcrest;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestSetPledgeCrestPreset extends ClientPacket
{
	private int _emblemType;
	private int _emblem;

	@Override
	protected void readImpl()
	{
		this._emblemType = this.readInt();
		this._emblem = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Clan clan = player.getClan();
			if (clan != null)
			{
				if (clan.getLevel() >= 3)
				{
					if (clan.getLeader().getObjectId() == player.getObjectId())
					{
						if (this._emblemType == 0)
						{
							clan.changeClanCrest(0);
							return;
						}

						if (this._emblemType == 1)
						{
							clan.changeClanCrest(this._emblem);
						}
					}
				}
			}
		}
	}
}
