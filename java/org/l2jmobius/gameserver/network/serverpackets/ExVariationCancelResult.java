package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExVariationCancelResult extends ServerPacket
{
	public static final ExVariationCancelResult STATIC_PACKET_SUCCESS = new ExVariationCancelResult(1);
	public static final ExVariationCancelResult STATIC_PACKET_FAILURE = new ExVariationCancelResult(0);
	private final int _result;

	private ExVariationCancelResult(int result)
	{
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_VARIATION_CANCEL_RESULT.writeId(this, buffer);
		buffer.writeInt(this._result);
	}
}
