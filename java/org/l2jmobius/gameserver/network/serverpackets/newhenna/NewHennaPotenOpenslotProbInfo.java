package org.l2jmobius.gameserver.network.serverpackets.newhenna;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class NewHennaPotenOpenslotProbInfo extends ServerPacket
{
	private final int _slot;

	public NewHennaPotenOpenslotProbInfo(Player player, int slot)
	{
		this._slot = slot;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_NEW_HENNA_POTEN_OPENSLOT_PROB_INFO.writeId(this, buffer);
		buffer.writeInt(this._slot);
		buffer.writeInt(10000);
		buffer.writeInt(0);
		buffer.writeInt(57);
		buffer.writeLong(10000L);
	}
}
