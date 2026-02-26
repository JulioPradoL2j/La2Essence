package org.l2jmobius.gameserver.network.serverpackets.revenge;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPvpBookShareRevengeKillerLocation extends ServerPacket
{
	private final Player _player;

	public ExPvpBookShareRevengeKillerLocation(Player player)
	{
		this._player = player;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PVPBOOK_SHARE_REVENGE_KILLER_LOCATION.writeId(this, buffer);
		buffer.writeSizedString(this._player.getName());
		buffer.writeInt(this._player.getX());
		buffer.writeInt(this._player.getY());
		buffer.writeInt(this._player.getZ());
	}
}
