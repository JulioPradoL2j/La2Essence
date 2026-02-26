package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExAdenaInvenCount extends ServerPacket
{
	private final Player _player;

	public ExAdenaInvenCount(Player player)
	{
		this._player = player;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ADENA_INVEN_COUNT.writeId(this, buffer);
		buffer.writeLong(this._player.getAdena());
		buffer.writeShort(this._player.getInventory().getSize());
	}
}
