package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.Party;
import org.l2jmobius.gameserver.model.groups.PartyDistributionType;

public class RequestPartyLootModification extends ClientPacket
{
	private int _partyDistributionTypeId;

	@Override
	protected void readImpl()
	{
		this._partyDistributionTypeId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			PartyDistributionType partyDistributionType = PartyDistributionType.findById(this._partyDistributionTypeId);
			if (partyDistributionType != null)
			{
				Party party = player.getParty();
				if (party != null && party.isLeader(player) && partyDistributionType != party.getDistributionType())
				{
					party.requestLootChange(partyDistributionType);
				}
			}
		}
	}
}
