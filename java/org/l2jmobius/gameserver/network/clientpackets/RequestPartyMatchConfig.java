package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.MatchingRoomManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.CommandChannel;
import org.l2jmobius.gameserver.model.groups.Party;
import org.l2jmobius.gameserver.model.groups.matching.CommandChannelMatchingRoom;
import org.l2jmobius.gameserver.model.groups.matching.PartyMatchingRoomLevelType;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ListPartyWaiting;

public class RequestPartyMatchConfig extends ClientPacket
{
	private int _page;
	private int _location;
	private PartyMatchingRoomLevelType _type;

	@Override
	protected void readImpl()
	{
		this._page = this.readInt();
		this._location = this.readInt();
		this._type = this.readInt() == 0 ? PartyMatchingRoomLevelType.MY_LEVEL_RANGE : PartyMatchingRoomLevelType.ALL;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Party party = player.getParty();
			CommandChannel cc = party == null ? null : party.getCommandChannel();
			if (party != null && cc != null && cc.getLeader() == player)
			{
				if (player.getMatchingRoom() == null)
				{
					player.setMatchingRoom(new CommandChannelMatchingRoom(player.getName(), party.getDistributionType().ordinal(), 1, player.getLevel(), 50, player));
				}
			}
			else if (cc != null && cc.getLeader() != player)
			{
				player.sendPacket(SystemMessageId.THE_COMMAND_CHANNEL_AFFILIATED_PARTY_S_PARTY_MEMBER_CANNOT_USE_THE_MATCHING_SCREEN);
			}
			else if (party != null && party.getLeader() != player)
			{
				player.sendPacket(SystemMessageId.THE_LIST_OF_PARTY_ROOMS_CAN_ONLY_BE_VIEWED_BY_A_PERSON_WHO_IS_NOT_PART_OF_A_PARTY);
			}
			else
			{
				MatchingRoomManager.getInstance().addToWaitingList(player);
				player.sendPacket(new ListPartyWaiting(this._type, this._location, this._page, player.getLevel()));
			}
		}
	}
}
