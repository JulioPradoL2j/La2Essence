package org.l2jmobius.gameserver.network.serverpackets.castlewar;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.model.siege.Siege;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class MercenaryCastleWarCastleSiegeInfo extends ServerPacket
{
	private final int _castleId;
	private final Castle _castle;

	public MercenaryCastleWarCastleSiegeInfo(int castleId)
	{
		this._castleId = castleId;
		this._castle = CastleManager.getInstance().getCastleById(castleId);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MERCENARY_CASTLEWAR_CASTLE_SIEGE_INFO.writeId(this, buffer);
		buffer.writeInt(this._castleId);
		if (this._castle == null)
		{
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeSizedString("-");
			buffer.writeSizedString("-");
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
		}
		else
		{
			buffer.writeInt(0);
			buffer.writeInt(0);
			Clan clan = this._castle.getOwner();
			buffer.writeSizedString(clan != null ? clan.getName() : "-");
			buffer.writeSizedString(clan != null ? clan.getLeaderName() : "-");
			buffer.writeInt(0);
			Siege siege = this._castle.getSiege();
			buffer.writeInt(siege.getAttackerClans().size());
			buffer.writeInt(siege.getDefenderClans().size() + siege.getDefenderWaitingClans().size());
		}
	}
}
