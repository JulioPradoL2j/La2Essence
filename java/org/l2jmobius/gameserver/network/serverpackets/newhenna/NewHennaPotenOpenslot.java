package org.l2jmobius.gameserver.network.serverpackets.newhenna;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class NewHennaPotenOpenslot extends ServerPacket
{
	private final boolean _success;
	private final int _dye;
	private final int _slot;
	private final int _stage;

	public NewHennaPotenOpenslot(boolean success, int dye, int slot, int stage)
	{
		this._dye = dye;
		this._slot = slot;
		this._success = success;
		this._stage = stage;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_NEW_HENNA_POTEN_OPENSLOT.writeId(this, buffer);
		buffer.writeInt(this._success);
		buffer.writeInt(this._dye);
		buffer.writeInt(this._slot);
		buffer.writeInt(this._stage);
	}
}
