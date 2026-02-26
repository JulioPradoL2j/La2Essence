package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.managers.FortManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.model.siege.Fort;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ExPledgeCount;
import org.l2jmobius.gameserver.network.serverpackets.JoinPledge;
import org.l2jmobius.gameserver.network.serverpackets.PledgeShowInfoUpdate;
import org.l2jmobius.gameserver.network.serverpackets.PledgeShowMemberListAdd;
import org.l2jmobius.gameserver.network.serverpackets.PledgeShowMemberListAll;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class RequestAnswerJoinPledge extends ClientPacket
{
	private int _answer;

	@Override
	protected void readImpl()
	{
		this._answer = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Player requestor = player.getRequest().getPartner();
			if (requestor != null)
			{
				if (this._answer == 0)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.YOU_DIDN_T_RESPOND_TO_S1_S_INVITATION_JOINING_HAS_BEEN_CANCELLED);
					sm.addString(requestor.getName());
					player.sendPacket(sm);
					sm = new SystemMessage(SystemMessageId.S1_DID_NOT_RESPOND_INVITATION_TO_THE_CLAN_HAS_BEEN_CANCELLED);
					sm.addString(player.getName());
					requestor.sendPacket(sm);
				}
				else
				{
					if (!(requestor.getRequest().getRequestPacket() instanceof RequestJoinPledge) && !(requestor.getRequest().getRequestPacket() instanceof RequestClanAskJoinByName))
					{
						return;
					}

					int pledgeType;
					if (requestor.getRequest().getRequestPacket() instanceof RequestJoinPledge)
					{
						pledgeType = ((RequestJoinPledge) requestor.getRequest().getRequestPacket()).getPledgeType();
					}
					else
					{
						pledgeType = ((RequestClanAskJoinByName) requestor.getRequest().getRequestPacket()).getPledgeType();
					}

					Clan clan = requestor.getClan();
					if (clan.checkClanJoinCondition(requestor, player, pledgeType))
					{
						if (player.getClan() != null)
						{
							return;
						}

						player.sendPacket(new JoinPledge(requestor.getClanId()));
						player.setPledgeType(pledgeType);
						if (pledgeType == -1)
						{
							player.setPowerGrade(9);
							player.setLvlJoinedAcademy(player.getLevel());
						}
						else
						{
							player.setPowerGrade(5);
						}

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
						player.broadcastUserInfo();
					}
				}

				player.getRequest().onRequestResponse();
			}
		}
	}
}
