package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExUserInfoCubic extends ServerPacket
{
	private final Player _player;

	public ExUserInfoCubic(Player player)
	{
		this._player = player;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_USER_INFO_CUBIC.writeId(this, buffer);
		buffer.writeInt(this._player.getObjectId());
		if (this._player.getAgathionId() > 0)
		{
			buffer.writeInt(this._player.getAgathionId());
			buffer.writeInt(0);
		}
		else
		{
			buffer.writeInt(0);
			buffer.writeInt(0);
		}

		buffer.writeShort(this._player.getCubics().size());
		this._player.getCubics().keySet().forEach(buffer::writeShort);
	}
}
