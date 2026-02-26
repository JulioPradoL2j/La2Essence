package org.l2jmobius.gameserver.network.serverpackets.payback;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class PaybackUILauncher extends ServerPacket
{
	private final boolean _isEnabled;

	public PaybackUILauncher(boolean isEnabled)
	{
		this._isEnabled = isEnabled;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PAYBACK_UI_LAUNCHER.writeId(this, buffer);
		buffer.writeByte(this._isEnabled);
	}
}
