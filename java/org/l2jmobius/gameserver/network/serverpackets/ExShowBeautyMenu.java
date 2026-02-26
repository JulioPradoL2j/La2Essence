package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExShowBeautyMenu extends ServerPacket
{
	public static final int MODIFY_APPEARANCE = 0;
	public static final int RESTORE_APPEARANCE = 1;
	private final Player _player;
	private final int _type;

	public ExShowBeautyMenu(Player player, int type)
	{
		this._player = player;
		this._type = type;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_BEAUTY_MENU.writeId(this, buffer);
		buffer.writeInt(this._type);
		buffer.writeInt(this._player.getVisualHair());
		buffer.writeInt(this._player.getVisualHairColor());
		buffer.writeInt(this._player.getVisualFace());
	}
}
