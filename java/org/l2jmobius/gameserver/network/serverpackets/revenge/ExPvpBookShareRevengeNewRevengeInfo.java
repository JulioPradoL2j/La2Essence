package org.l2jmobius.gameserver.network.serverpackets.revenge;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.enums.RevengeType;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPvpBookShareRevengeNewRevengeInfo extends ServerPacket
{
	private final String _victimName;
	private final String _killerName;
	private final RevengeType _type;

	public ExPvpBookShareRevengeNewRevengeInfo(String victimName, String killerName, RevengeType type)
	{
		this._victimName = victimName;
		this._killerName = killerName;
		this._type = type;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PVPBOOK_SHARE_REVENGE_NEW_REVENGEINFO.writeId(this, buffer);
		buffer.writeInt(this._type.ordinal());
		buffer.writeSizedString(this._victimName);
		buffer.writeSizedString(this._killerName);
	}
}
