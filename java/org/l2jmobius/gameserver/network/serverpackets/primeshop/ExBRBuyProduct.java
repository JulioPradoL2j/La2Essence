package org.l2jmobius.gameserver.network.serverpackets.primeshop;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.enums.ExBrProductReplyType;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExBRBuyProduct extends ServerPacket
{
	private final int _reply;

	public ExBRBuyProduct(ExBrProductReplyType type)
	{
		this._reply = type.getId();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BR_BUY_PRODUCT_ACK.writeId(this, buffer);
		buffer.writeInt(this._reply);
	}
}
