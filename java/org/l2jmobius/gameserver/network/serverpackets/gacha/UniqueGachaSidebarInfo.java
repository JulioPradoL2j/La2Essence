package org.l2jmobius.gameserver.network.serverpackets.gacha;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class UniqueGachaSidebarInfo extends ServerPacket
{
	public static final UniqueGachaSidebarInfo GACHA_ON = new UniqueGachaSidebarInfo(true);
	public static final UniqueGachaSidebarInfo GACHA_OFF = new UniqueGachaSidebarInfo(false);
	private final boolean _turnedOn;

	private UniqueGachaSidebarInfo(boolean turnedOn)
	{
		this._turnedOn = turnedOn;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_UNIQUE_GACHA_SIDEBAR_INFO.writeId(this, buffer);
		buffer.writeByte(this._turnedOn);
	}
}
