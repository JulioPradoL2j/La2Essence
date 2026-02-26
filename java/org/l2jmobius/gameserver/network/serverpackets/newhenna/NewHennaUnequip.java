package org.l2jmobius.gameserver.network.serverpackets.newhenna;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class NewHennaUnequip extends ServerPacket
{
	private final int _slotId;
	private final int _success;

	public NewHennaUnequip(int slotId, int success)
	{
		this._slotId = slotId;
		this._success = success;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_NEW_HENNA_UNEQUIP.writeId(this, buffer);
		buffer.writeByte(this._slotId);
		buffer.writeByte(this._success);
	}
}
