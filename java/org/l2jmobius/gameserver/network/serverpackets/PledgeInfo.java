package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.ServerConfig;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PledgeInfo extends ServerPacket
{
	private final Clan _clan;

	public PledgeInfo(Clan clan)
	{
		this._clan = clan;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PLEDGE_INFO.writeId(this, buffer);
		buffer.writeInt(ServerConfig.SERVER_ID);
		buffer.writeInt(this._clan.getId());
		buffer.writeString(this._clan.getName());
		buffer.writeString(this._clan.getAllyName());
	}
}
