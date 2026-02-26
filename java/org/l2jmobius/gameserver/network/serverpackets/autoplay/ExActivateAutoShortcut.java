package org.l2jmobius.gameserver.network.serverpackets.autoplay;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.holders.player.Shortcut;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExActivateAutoShortcut extends ServerPacket
{
	private final int _position;
	private final boolean _active;

	public ExActivateAutoShortcut(Shortcut shortcut, boolean active)
	{
		this._position = shortcut.getSlot() + shortcut.getPage() * 12;
		this._active = active;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ACTIVATE_AUTO_SHORTCUT.writeId(this, buffer);
		buffer.writeShort(this._position);
		buffer.writeByte(this._active);
	}
}
