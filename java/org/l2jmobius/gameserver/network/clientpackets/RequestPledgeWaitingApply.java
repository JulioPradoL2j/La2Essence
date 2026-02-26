package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.data.sql.ClanTable;
import org.l2jmobius.gameserver.managers.ClanEntryManager;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.clan.entry.PledgeApplicantInfo;
import org.l2jmobius.gameserver.model.clan.enums.ClanEntryStatus;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ExPledgeRecruitApplyInfo;
import org.l2jmobius.gameserver.network.serverpackets.ExPledgeWaitingListAlarm;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class RequestPledgeWaitingApply extends ClientPacket
{
	private int _karma;
	private int _clanId;
	private String _message;

	@Override
	protected void readImpl()
	{
		this._karma = this.readInt();
		this._clanId = this.readInt();
		this._message = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && player.getClan() == null)
		{
			Clan clan = ClanTable.getInstance().getClan(this._clanId);
			if (clan != null)
			{
				PledgeApplicantInfo info = new PledgeApplicantInfo(player.getObjectId(), player.getName(), player.getLevel(), this._karma, this._clanId, this._message);
				if (ClanEntryManager.getInstance().addPlayerApplicationToClan(this._clanId, info))
				{
					player.sendPacket(new ExPledgeRecruitApplyInfo(ClanEntryStatus.WAITING));
					Player clanLeader = World.getInstance().getPlayer(clan.getLeaderId());
					if (clanLeader != null)
					{
						clanLeader.sendPacket(ExPledgeWaitingListAlarm.STATIC_PACKET);
					}
				}
				else
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.YOU_MAY_APPLY_FOR_ENTRY_IN_S1_MIN_AFTER_CANCELLING_YOUR_APPLICATION);
					sm.addLong(ClanEntryManager.getInstance().getPlayerLockTime(player.getObjectId()));
					player.sendPacket(sm);
				}
			}
		}
	}
}
