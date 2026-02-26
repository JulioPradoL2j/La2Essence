package org.l2jmobius.gameserver.network.serverpackets.newhenna;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class NewHennaEquip extends ServerPacket
{
	private final int _slotId;
	private final int _hennaId;
	private final boolean _success;

	public NewHennaEquip(int slotId, int hennaId, boolean success)
	{
		this._slotId = slotId;
		this._hennaId = hennaId;
		this._success = success;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_NEW_HENNA_EQUIP.writeId(this, buffer);
		buffer.writeByte(this._slotId);
		buffer.writeInt(this._hennaId);
		buffer.writeByte(this._success);
	}
}
