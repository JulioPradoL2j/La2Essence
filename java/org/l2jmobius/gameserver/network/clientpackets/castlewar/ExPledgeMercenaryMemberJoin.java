package org.l2jmobius.gameserver.network.clientpackets.castlewar;

import org.l2jmobius.gameserver.managers.SiegeManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.siege.Siege;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.castlewar.MercenaryCastleWarCastleSiegeAttackerList;
import org.l2jmobius.gameserver.network.serverpackets.castlewar.MercenaryCastleWarCastleSiegeDefenderList;

public class ExPledgeMercenaryMemberJoin extends ClientPacket
{
	private int _castleId;
	private boolean _type;
	private int _pledgeId;

	@Override
	protected void readImpl()
	{
		this.readInt();
		this._type = this.readInt() == 1;
		this._castleId = this.readInt();
		this._pledgeId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		Siege siege = SiegeManager.getInstance().getSiege(this._castleId);
		if (siege == null || !siege.isInProgress())
		{
			if (this._type)
			{
				if (player.getParty() != null)
				{
					player.sendPacket(SystemMessageId.A_CHARACTER_WHICH_IS_A_MEMBER_OF_A_PARTY_CANNOT_FILE_A_MERCENARY_REQUEST);
					return;
				}

				if (player.isMercenary())
				{
					player.sendPacket(SystemMessageId.THE_CHARACTER_IS_PARTICIPATING_AS_A_MERCENARY);
					return;
				}

				if (player.getLevel() < 40)
				{
					player.sendPacket(SystemMessageId.YOUR_CHARACTER_DOES_NOT_MEET_THE_LEVEL_REQUIREMENTS_TO_BE_A_MERCENARY);
					return;
				}

				if (player.getClan() != null)
				{
					Clan clan = player.getClan();
					if (clan.getId() == this._pledgeId)
					{
						player.sendPacket(SystemMessageId.YOU_CANNOT_BE_A_MERCENARY_AT_THE_CLAN_YOU_ARE_A_MEMBER_OF);
						return;
					}

					if (siege != null && (siege.checkIsAttacker(clan) || siege.checkIsDefender(clan)))
					{
						player.sendPacket(SystemMessageId.ATTACKERS_AND_DEFENDERS_CANNOT_BE_RECRUITED_AS_MERCENARIES);
						return;
					}
				}
			}

			player.setMercenary(this._type, this._pledgeId);
			player.sendPacket(new MercenaryCastleWarCastleSiegeAttackerList(this._castleId));
			player.sendPacket(new MercenaryCastleWarCastleSiegeDefenderList(this._castleId));
		}
	}
}
