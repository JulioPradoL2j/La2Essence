package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.data.sql.ClanTable;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.AskJoinAlly;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class RequestJoinAlly extends ClientPacket
{
	private String _sClanName;

	@Override
	protected void readImpl()
	{
		this._sClanName = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Clan clan = ClanTable.getInstance().getClanByName(this._sClanName);
			if (clan == null)
			{
				player.sendPacket(SystemMessageId.CLAN_NAME_IS_INVALID);
			}
			else
			{
				Player target = World.getInstance().getPlayer(clan.getLeaderId());
				if (target == null)
				{
					player.sendPacket(SystemMessageId.THE_TARGET_CANNOT_BE_INVITED);
				}
				else if (clan.checkAllyJoinCondition(player, target))
				{
					if (player.getRequest().setRequest(target, this))
					{
						SystemMessage sm = new SystemMessage(SystemMessageId.S1_LEADER_S2_HAS_REQUESTED_AN_ALLIANCE);
						sm.addString(player.getClan().getAllyName());
						sm.addString(player.getName());
						target.sendPacket(sm);
						target.sendPacket(new AskJoinAlly(player.getObjectId(), player.getName(), player.getClan().getAllyName()));
					}
				}
			}
		}
	}
}
