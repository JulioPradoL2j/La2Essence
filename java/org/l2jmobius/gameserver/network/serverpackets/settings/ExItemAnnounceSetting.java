package org.l2jmobius.gameserver.network.serverpackets.settings;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExItemAnnounceSetting extends ServerPacket
{
	private final boolean _announceDisabled;

	public ExItemAnnounceSetting(boolean announceDisabled)
	{
		this._announceDisabled = announceDisabled;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ITEM_ANNOUNCE_SETTING.writeId(this, buffer);
		buffer.writeByte(this._announceDisabled);
	}
}
