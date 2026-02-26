package org.l2jmobius.gameserver.network.serverpackets.newhenna;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class NewHennaPotenEnchantReset extends ServerPacket
{
	private final boolean _success;

	public NewHennaPotenEnchantReset(boolean success)
	{
		this._success = success;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_NEW_HENNA_POTEN_ENCHANT_RESET.writeId(this, buffer);
		buffer.writeByte(this._success);
	}
}
