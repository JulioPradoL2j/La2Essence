package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.data.sql.ClanTable;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.clan.ClanAccess;
import org.l2jmobius.gameserver.model.clan.ClanMember;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import org.l2jmobius.gameserver.network.serverpackets.PledgeReceiveWarList;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.taskmanagers.AttackStanceTaskManager;

public class RequestStopPledgeWar extends ClientPacket
{
	private String _pledgeName;

	@Override
	protected void readImpl()
	{
		this._pledgeName = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Clan playerClan = player.getClan();
			if (playerClan != null)
			{
				Clan clan = ClanTable.getInstance().getClanByName(this._pledgeName);
				if (clan == null)
				{
					player.sendMessage("No such clan.");
					player.sendPacket(ActionFailed.STATIC_PACKET);
				}
				else if (!playerClan.isAtWarWith(clan.getId()))
				{
					player.sendMessage("You aren't at war with this clan.");
					player.sendPacket(ActionFailed.STATIC_PACKET);
				}
				else if (!player.hasAccess(ClanAccess.WAR_DECLARATION))
				{
					player.sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				}
				else if (player.getClan().getReputationScore() <= 500)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.THE_CLAN_REPUTATION_IS_TOO_LOW);
					player.sendPacket(sm);
					player.sendPacket(ActionFailed.STATIC_PACKET);
				}
				else
				{
					for (ClanMember member : playerClan.getMembers())
					{
						if (member != null && member.getPlayer() != null && AttackStanceTaskManager.getInstance().hasAttackStanceTask(member.getPlayer()))
						{
							player.sendPacket(SystemMessageId.THE_CLAN_WAR_CANNOT_BE_STOPPED_BECAUSE_SOMEONE_FROM_YOUR_CLAN_IS_STILL_ENGAGED_IN_BATTLE);
							player.sendPacket(ActionFailed.STATIC_PACKET);
							return;
						}
					}

					playerClan.takeReputationScore(500);
					SystemMessage sm = new SystemMessage(SystemMessageId.YOUR_CLAN_LOST_500_REPUTATION_POINTS_FOR_WITHDRAWING_FROM_THE_CLAN_WAR);
					player.getClan().broadcastToOnlineMembers(sm);
					ClanTable.getInstance().deleteClanWars(playerClan.getId(), clan.getId());

					for (Player memberx : playerClan.getOnlineMembers(0))
					{
						memberx.broadcastUserInfo();
					}

					for (Player memberx : clan.getOnlineMembers(0))
					{
						memberx.broadcastUserInfo();
					}

					player.sendPacket(new PledgeReceiveWarList(player.getClan(), 0));
				}
			}
		}
	}
}
