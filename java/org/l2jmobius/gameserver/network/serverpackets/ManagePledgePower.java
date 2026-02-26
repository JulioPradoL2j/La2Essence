package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ManagePledgePower extends ServerPacket
{
	private final int _action;
	private final Clan _clan;
	private final int _rank;

	public ManagePledgePower(Clan clan, int action, int rank)
	{
		this._clan = clan;
		this._action = action;
		this._rank = rank;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.MANAGE_PLEDGE_POWER.writeId(this, buffer);
		buffer.writeInt(this._rank);
		buffer.writeInt(this._action);
		buffer.writeInt(this._clan.getRankPrivs(this._rank).getMask());
	}
}
