package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExSendManorList extends ServerPacket
{
	public static final ExSendManorList STATIC_PACKET = new ExSendManorList();
	private final Collection<Castle> _castles = CastleManager.getInstance().getCastles();

	private ExSendManorList()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SEND_MANOR_LIST.writeId(this, buffer);
		buffer.writeInt(this._castles.size());

		for (Castle castle : this._castles)
		{
			buffer.writeInt(castle.getResidenceId());
		}
	}
}
