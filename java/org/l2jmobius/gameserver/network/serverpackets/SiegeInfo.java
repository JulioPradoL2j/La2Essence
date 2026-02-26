package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Calendar;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.FeatureConfig;
import org.l2jmobius.gameserver.data.sql.ClanTable;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.ServerPackets;

public class SiegeInfo extends ServerPacket
{
	private final Castle _castle;
	private final Player _player;

	public SiegeInfo(Castle castle, Player player)
	{
		this._castle = castle;
		this._player = player;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.CASTLE_SIEGE_INFO.writeId(this, buffer);
		if (this._castle != null)
		{
			buffer.writeInt(this._castle.getResidenceId());
			int ownerId = this._castle.getOwnerId();
			buffer.writeInt(ownerId == this._player.getClanId() && this._player.isClanLeader());
			buffer.writeInt(ownerId);
			if (ownerId > 0)
			{
				Clan owner = ClanTable.getInstance().getClan(ownerId);
				if (owner != null)
				{
					buffer.writeString(owner.getName());
					buffer.writeString(owner.getLeaderName());
					buffer.writeInt(owner.getAllyId());
					buffer.writeString(owner.getAllyName());
				}
				else
				{
					PacketLogger.warning("Null owner for castle: " + this._castle.getName());
				}
			}
			else
			{
				buffer.writeString("");
				buffer.writeString("");
				buffer.writeInt(0);
				buffer.writeString("");
			}

			buffer.writeInt((int) (System.currentTimeMillis() / 1000L));
			if (!this._castle.isTimeRegistrationOver() && this._player.isClanLeader() && this._player.getClanId() == this._castle.getOwnerId())
			{
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(this._castle.getSiegeDate().getTimeInMillis());
				cal.set(12, 0);
				cal.set(13, 0);
				buffer.writeInt(0);
				buffer.writeInt(FeatureConfig.SIEGE_HOUR_LIST.size());

				for (int hour : FeatureConfig.SIEGE_HOUR_LIST)
				{
					cal.set(11, hour);
					buffer.writeInt((int) (cal.getTimeInMillis() / 1000L));
				}
			}
			else
			{
				buffer.writeInt((int) (this._castle.getSiegeDate().getTimeInMillis() / 1000L));
				buffer.writeInt(0);
			}
		}
	}
}
