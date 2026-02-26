package org.l2jmobius.gameserver.network.clientpackets.revenge;

import org.l2jmobius.gameserver.managers.RevengeHistoryManager;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class RequestExPvpBookShareRevengeReqShareRevengeInfo extends ClientPacket
{
	private String _victimName;
	private String _killerName;
	private int _type;

	@Override
	protected void readImpl()
	{
		this._victimName = this.readSizedString();
		this._killerName = this.readSizedString();
		this._type = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._victimName.equals(player.getName()))
			{
				Player killer = World.getInstance().getPlayer(this._killerName);
				if (killer != null && killer.isOnline())
				{
					RevengeHistoryManager.getInstance().requestHelp(player, killer, this._type);
				}
				else
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.S1_OFFLINE);
					sm.addString(this._killerName);
					player.sendPacket(sm);
				}
			}
		}
	}
}
