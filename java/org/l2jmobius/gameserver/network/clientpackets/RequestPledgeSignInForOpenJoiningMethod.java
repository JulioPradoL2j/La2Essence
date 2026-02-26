package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.managers.ClanEntryManager;
import org.l2jmobius.gameserver.managers.FortManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.clan.entry.PledgeRecruitInfo;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.model.siege.Fort;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ExPledgeCount;
import org.l2jmobius.gameserver.network.serverpackets.JoinPledge;
import org.l2jmobius.gameserver.network.serverpackets.PledgeShowInfoUpdate;
import org.l2jmobius.gameserver.network.serverpackets.PledgeShowMemberListAdd;
import org.l2jmobius.gameserver.network.serverpackets.PledgeShowMemberListAll;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class RequestPledgeSignInForOpenJoiningMethod extends ClientPacket
{
	private int _clanId;

	@Override
	protected void readImpl()
	{
		this._clanId = this.readInt();
		this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			PledgeRecruitInfo pledgeRecruitInfo = ClanEntryManager.getInstance().getClanById(this._clanId);
			if (pledgeRecruitInfo != null)
			{
				Clan clan = pledgeRecruitInfo.getClan();
				if (clan != null && player.getClan() == null)
				{
					if (clan.getCharPenaltyExpiryTime() > System.currentTimeMillis())
					{
						player.sendPacket(SystemMessageId.YOU_CANNOT_ACCEPT_A_NEW_CLAN_MEMBER_FOR_24_H_AFTER_DISMISSING_SOMEONE);
						return;
					}

					if (player.getClanJoinExpiryTime() > System.currentTimeMillis())
					{
						SystemMessage sm = new SystemMessage(SystemMessageId.C1_WILL_BE_ABLE_TO_JOIN_YOUR_CLAN_IN_S2_MIN_AFTER_LEAVING_THE_PREVIOUS_ONE);
						sm.addString(player.getName());
						sm.addInt(PlayerConfig.ALT_CLAN_JOIN_MINS);
						player.sendPacket(sm);
						return;
					}

					if (clan.getSubPledgeMembersCount(0) >= clan.getMaxNrOfMembers(0))
					{
						SystemMessage sm = new SystemMessage(SystemMessageId.S1_IS_FULL_AND_CANNOT_ACCEPT_ADDITIONAL_CLAN_MEMBERS_AT_THIS_TIME);
						sm.addString(clan.getName());
						player.sendPacket(sm);
						return;
					}

					player.sendPacket(new JoinPledge(clan.getId()));
					player.setPowerGrade(5);
					clan.addClanMember(player);
					player.setClanPrivileges(player.getClan().getRankPrivs(player.getPowerGrade()));
					player.sendPacket(SystemMessageId.ENTERED_THE_CLAN);
					SystemMessage sm = new SystemMessage(SystemMessageId.S1_HAS_JOINED_THE_CLAN);
					sm.addString(player.getName());
					clan.broadcastToOnlineMembers(sm);
					if (clan.getCastleId() > 0)
					{
						Castle castle = CastleManager.getInstance().getCastleByOwner(clan);
						if (castle != null)
						{
							castle.giveResidentialSkills(player);
						}
					}

					if (clan.getFortId() > 0)
					{
						Fort fort = FortManager.getInstance().getFortByOwner(clan);
						if (fort != null)
						{
							fort.giveResidentialSkills(player);
						}
					}

					player.sendSkillList();
					clan.broadcastToOtherOnlineMembers(new PledgeShowMemberListAdd(player), player);
					clan.broadcastToOnlineMembers(new PledgeShowInfoUpdate(clan));
					clan.broadcastToOnlineMembers(new ExPledgeCount(clan));
					PledgeShowMemberListAll.sendAllTo(player);
					player.setClanJoinExpiryTime(0L);
					player.setClanJoinTime(System.currentTimeMillis());
					player.broadcastUserInfo();
					ClanEntryManager.getInstance().removePlayerApplication(this._clanId, player.getObjectId());
				}
			}
		}
	}
}
