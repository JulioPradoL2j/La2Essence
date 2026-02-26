package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.model.siege.CastleSide;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExCastleState extends ServerPacket
{
	private final int _castleId;
	private final CastleSide _castleSide;

	public ExCastleState(Castle castle)
	{
		this._castleId = castle.getResidenceId();
		this._castleSide = castle.getSide();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CASTLE_STATE.writeId(this, buffer);
		buffer.writeInt(this._castleId);
		buffer.writeInt(this._castleSide.ordinal());
	}
}
