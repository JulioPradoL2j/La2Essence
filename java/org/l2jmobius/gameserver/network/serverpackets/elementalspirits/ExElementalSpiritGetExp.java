package org.l2jmobius.gameserver.network.serverpackets.elementalspirits;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExElementalSpiritGetExp extends ServerPacket
{
	private final long _experience;
	private final byte _type;

	public ExElementalSpiritGetExp(byte type, long experience)
	{
		this._type = type;
		this._experience = experience;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ELEMENTAL_SPIRIT_GET_EXP.writeId(this, buffer);
		buffer.writeByte(this._type);
		buffer.writeLong(this._experience);
	}
}
