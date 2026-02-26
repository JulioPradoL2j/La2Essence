package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.instance.Door;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class DoorInfo extends ServerPacket
{
	private final Door _door;

	public DoorInfo(Door door)
	{
		this._door = door;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.DOOR_INFO.writeId(this, buffer);
		buffer.writeInt(this._door.getObjectId());
		buffer.writeInt(this._door.getId());
	}
}
