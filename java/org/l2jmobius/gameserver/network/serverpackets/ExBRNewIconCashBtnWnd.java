package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExBRNewIconCashBtnWnd extends ServerPacket
{
	private final short _active;

	public ExBRNewIconCashBtnWnd(short active)
	{
		this._active = active;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BR_EXIST_NEW_PRODUCT_ACK.writeId(this, buffer);
		buffer.writeShort(this._active);
	}
}
