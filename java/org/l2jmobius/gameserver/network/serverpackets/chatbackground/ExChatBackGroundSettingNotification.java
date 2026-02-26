package org.l2jmobius.gameserver.network.serverpackets.chatbackground;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.variables.PlayerVariables;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExChatBackGroundSettingNotification extends ServerPacket
{
	private final int _activeBackground;
	private final boolean _enabled;

	public ExChatBackGroundSettingNotification(PlayerVariables variables)
	{
		this._activeBackground = variables.getInt("ACTIVE_CHAT_BACKGROUND", 0);
		this._enabled = variables.getBoolean("ENABLE_CHAT_BACKGROUND", false);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHARACTER_STYLE_USE_ITEM.writeId(this, buffer);
		buffer.writeInt(this._activeBackground);
		buffer.writeByte(this._enabled);
	}
}
