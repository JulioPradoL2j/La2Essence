package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Set;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExMPCCPartymasterList extends ServerPacket
{
	private final Set<String> _leadersName;

	public ExMPCCPartymasterList(Set<String> leadersName)
	{
		this._leadersName = leadersName;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MPCC_PARTYMASTER_LIST.writeId(this, buffer);
		buffer.writeInt(this._leadersName.size());
		this._leadersName.forEach(buffer::writeString);
	}
}
