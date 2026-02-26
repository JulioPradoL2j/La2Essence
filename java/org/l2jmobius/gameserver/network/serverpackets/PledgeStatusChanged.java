package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PledgeStatusChanged extends ServerPacket
{
	private final Clan _clan;

	public PledgeStatusChanged(Clan clan)
	{
		this._clan = clan;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PLEDGE_STATUS_CHANGED.writeId(this, buffer);
		buffer.writeInt(0);
		buffer.writeInt(this._clan.getLeaderId());
		buffer.writeInt(this._clan.getId());
		buffer.writeInt(this._clan.getCrestId());
		buffer.writeInt(this._clan.getAllyId());
		buffer.writeInt(this._clan.getAllyCrestId());
		buffer.writeInt(this._clan.getCrestLargeId());
		buffer.writeInt(0);
	}
}
