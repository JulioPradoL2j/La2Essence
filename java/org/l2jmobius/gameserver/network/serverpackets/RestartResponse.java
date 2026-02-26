package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class RestartResponse extends ServerPacket
{
	public static final RestartResponse TRUE = new RestartResponse(true);
	public static final RestartResponse FALSE = new RestartResponse(false);
	private final boolean _result;

	private RestartResponse(boolean result)
	{
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.RESTART_RESPONSE.writeId(this, buffer);
		buffer.writeInt(this._result);
	}
}
