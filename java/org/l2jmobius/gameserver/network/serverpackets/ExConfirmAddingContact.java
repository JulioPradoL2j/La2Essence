package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExConfirmAddingContact extends ServerPacket
{
	private final String _charName;
	private final boolean _added;

	public ExConfirmAddingContact(String charName, boolean added)
	{
		this._charName = charName;
		this._added = added;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_AGITAUCTION_CMD.writeId(this, buffer);
		buffer.writeString(this._charName);
		buffer.writeInt(this._added);
	}
}
