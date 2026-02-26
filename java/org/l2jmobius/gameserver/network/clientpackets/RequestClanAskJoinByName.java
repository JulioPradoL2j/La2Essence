package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.serverpackets.AskJoinPledge;

public class RequestClanAskJoinByName extends ClientPacket
{
	private String _playerName;
	private int _pledgeType;

	@Override
	protected void readImpl()
	{
		this._playerName = this.readString();
		this._pledgeType = this.readInt();
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
				Player invitedPlayer = World.getInstance().getPlayer(this._playerName);
				if (clan.checkClanJoinCondition(player, invitedPlayer, this._pledgeType))
				{
					if (player.getRequest().setRequest(invitedPlayer, this))
					{
						invitedPlayer.sendPacket(new AskJoinPledge(player, clan.getName()));
					}
				}
			}
		}
	}

	public int getPledgeType()
	{
		return this._pledgeType;
	}
}
