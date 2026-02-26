package org.l2jmobius.gameserver.network.serverpackets.dualinventory;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExDualInventorySwap extends ServerPacket
{
	private final int _slot;

	public ExDualInventorySwap(int slot)
	{
		this._slot = slot;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_DUAL_INVENTORY_INFO.writeId(this, buffer);
		buffer.writeByte(this._slot);
		buffer.writeByte(1);
		buffer.writeByte(1);
		buffer.writeByte(0);
		buffer.writeInt(0);
		buffer.writeInt(0);
		buffer.writeByte(0);
		buffer.writeInt(0);
		buffer.writeInt(0);
		buffer.writeByte(0);
		buffer.writeByte(0);
	}
}
