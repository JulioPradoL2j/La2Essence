package org.l2jmobius.gameserver.network.serverpackets.castlewar;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.sql.ClanTable;
import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.model.SiegeClan;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class MercenaryCastleWarCastleSiegeAttackerList extends ServerPacket
{
	private final int _castleId;
	private final Castle _castle;
	private final List<Clan> _attackers = new ArrayList<>();

	public MercenaryCastleWarCastleSiegeAttackerList(int castleId)
	{
		this._castleId = castleId;
		this._castle = CastleManager.getInstance().getCastleById(castleId);

		for (SiegeClan siegeclan : this._castle.getSiege().getAttackerClans())
		{
			Clan clan = ClanTable.getInstance().getClan(siegeclan.getClanId());
			if (clan != null)
			{
				this._attackers.add(clan);
			}
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MERCENARY_CASTLEWAR_CASTLE_SIEGE_ATTACKER_LIST.writeId(this, buffer);
		buffer.writeInt(this._castleId);
		buffer.writeInt(0);
		buffer.writeInt(1);
		buffer.writeInt(0);
		if (this._castle == null)
		{
			buffer.writeInt(0);
			buffer.writeInt(0);
		}
		else
		{
			buffer.writeInt(this._attackers.size());
			buffer.writeInt(this._attackers.size());

			for (Clan clan : this._attackers)
			{
				buffer.writeInt(clan.getId());
				buffer.writeString(clan.getName());
				buffer.writeString(clan.getLeaderName());
				buffer.writeInt(clan.getCrestId());
				buffer.writeInt(0);
				buffer.writeInt(clan.isRecruitMercenary());
				buffer.writeLong(clan.getRewardMercenary());
				buffer.writeInt(clan.getMapMercenary().size());
				buffer.writeLong(0L);
				buffer.writeLong(0L);
				if (clan.getAllyId() != 0)
				{
					buffer.writeInt(clan.getAllyId());
					buffer.writeString(clan.getAllyName());
					buffer.writeString("");
					buffer.writeInt(clan.getAllyCrestId());
				}
				else
				{
					buffer.writeInt(0);
					buffer.writeString("");
					buffer.writeString("");
					buffer.writeInt(0);
				}
			}
		}
	}
}
