package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPledgeCount extends ServerPacket
{
	private final int _count;

	public ExPledgeCount(Clan clan)
	{
		this._count = clan.getOnlineMembersCount();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_COUNT.writeId(this, buffer);
		buffer.writeInt(this._count);
	}
}
