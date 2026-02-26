package org.l2jmobius.gameserver.network.clientpackets.castlewar;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExPledgeMercenaryRecruitInfoSet extends ClientPacket
{
	private boolean _isRecruit;
	private int _mercenaryReaward;

	@Override
	protected void readImpl()
	{
		this.readInt();
		this.readInt();
		this._isRecruit = this.readInt() == 1;
		this._mercenaryReaward = this.readInt();
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
				if (!clan.isRecruitMercenary() || clan.getMapMercenary().size() <= 0)
				{
					clan.setRecruitMercenary(this._isRecruit);
					clan.setRewardMercenary(this._mercenaryReaward);
				}
			}
		}
	}
}
