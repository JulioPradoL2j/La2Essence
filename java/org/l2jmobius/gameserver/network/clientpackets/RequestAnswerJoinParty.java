package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.PartyRequest;
import org.l2jmobius.gameserver.model.groups.Party;
import org.l2jmobius.gameserver.model.groups.PartyMessageType;
import org.l2jmobius.gameserver.model.groups.matching.MatchingRoom;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.JoinParty;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class RequestAnswerJoinParty extends ClientPacket
{
	private int _response;

	@Override
	protected void readImpl()
	{
		this._response = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			PartyRequest request = player.getRequest(PartyRequest.class);
			if (request != null && !request.isProcessing() && player.removeRequest(request.getClass()))
			{
				request.setProcessing(true);
				Player requestor = request.getPlayer();
				if (requestor != null)
				{
					Party party = request.getParty();
					Party requestorParty = requestor.getParty();
					if (requestorParty == null || requestorParty == party)
					{
						requestor.sendPacket(new JoinParty(this._response, requestor));
						if (this._response == 1)
						{
							if (party.getMemberCount() >= PlayerConfig.ALT_PARTY_MAX_MEMBERS)
							{
								SystemMessage sm = new SystemMessage(SystemMessageId.THE_PARTY_IS_FULL);
								player.sendPacket(sm);
								requestor.sendPacket(sm);
								return;
							}

							if (requestorParty == null)
							{
								requestor.setParty(party);
							}

							player.joinParty(party);
							MatchingRoom requestorRoom = requestor.getMatchingRoom();
							if (requestorRoom != null)
							{
								requestorRoom.addMember(player);
							}
						}
						else if (this._response == -1)
						{
							SystemMessage sm = new SystemMessage(SystemMessageId.C1_IS_SET_TO_REFUSE_PARTY_REQUESTS_AND_CANNOT_RECEIVE_A_PARTY_REQUEST);
							sm.addPcName(player);
							requestor.sendPacket(sm);
							if (party.getMemberCount() == 1)
							{
								party.removePartyMember(requestor, PartyMessageType.NONE);
							}
						}
						else if (party.getMemberCount() == 1)
						{
							party.removePartyMember(requestor, PartyMessageType.NONE);
						}

						party.setPendingInvitation(false);
						request.setProcessing(false);
					}
				}
			}
		}
	}
}
