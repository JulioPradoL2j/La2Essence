package org.l2jmobius.gameserver.network.serverpackets.castlewar;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.managers.SiegeManager;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class MercenaryCastleWarCastleSiegeHudInfo extends ServerPacket
{
	private final Castle _castle;

	public MercenaryCastleWarCastleSiegeHudInfo(int castleId)
	{
		this._castle = CastleManager.getInstance().getCastleById(castleId);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		if (this._castle != null)
		{
			ServerPackets.EX_MERCENARY_CASTLEWAR_CASTLE_SIEGE_HUD_INFO.writeId(this, buffer);
			buffer.writeInt(this._castle.getResidenceId());
			if (this._castle.getSiege().isInProgress())
			{
				buffer.writeInt(1);
				buffer.writeInt(0);
				buffer.writeInt((int) ((this._castle.getSiegeDate().getTimeInMillis() + SiegeManager.getInstance().getSiegeLength() * 60000 - System.currentTimeMillis()) / 1000L));
			}
			else
			{
				buffer.writeInt(0);
				buffer.writeInt(0);
				buffer.writeInt((int) ((this._castle.getSiegeDate().getTimeInMillis() - System.currentTimeMillis()) / 1000L));
			}
		}
	}
}
