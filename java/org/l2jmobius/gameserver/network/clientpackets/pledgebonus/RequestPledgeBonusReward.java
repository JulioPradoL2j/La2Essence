package org.l2jmobius.gameserver.network.clientpackets.pledgebonus;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.clan.ClanMember;
import org.l2jmobius.gameserver.model.clan.ClanRewardBonus;
import org.l2jmobius.gameserver.model.clan.enums.ClanRewardType;
import org.l2jmobius.gameserver.model.skill.holders.SkillHolder;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestPledgeBonusReward extends ClientPacket
{
	private int _type;

	@Override
	protected void readImpl()
	{
		this._type = this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && player.getClan() != null)
		{
			if (this._type >= 0 && this._type <= ClanRewardType.values().length)
			{
				Clan clan = player.getClan();
				ClanRewardType type = ClanRewardType.values()[this._type];
				ClanMember member = clan.getClanMember(player.getObjectId());
				if (clan.canClaimBonusReward(player, type))
				{
					ClanRewardBonus bonus = type.getAvailableBonus(player.getClan());
					if (bonus != null)
					{
						SkillHolder skillReward = bonus.getSkillReward();
						if (skillReward != null)
						{
							skillReward.getSkill().activateSkill(player, player);
						}

						member.setRewardClaimed(type);
					}
					else
					{
						PacketLogger.warning(player + " Attempting to claim reward but clan(" + clan + ") doesn't have such!");
					}
				}
			}
		}
	}
}
