package org.l2jmobius.gameserver.network.serverpackets.friend;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class FriendStatus extends ServerPacket
{
	public static final int MODE_OFFLINE = 0;
	public static final int MODE_ONLINE = 1;
	public static final int MODE_LEVEL = 2;
	public static final int MODE_CLASS = 3;
	private final int _type;
	private final int _objectId;
	private final int _classId;
	private final int _level;
	private final String _name;

	public FriendStatus(Player player, int type)
	{
		this._objectId = player.getObjectId();
		this._classId = player.getActiveClass();
		this._level = player.getLevel();
		this._name = player.getName();
		this._type = type;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.FRIEND_STATUS.writeId(this, buffer);
		buffer.writeInt(this._type);
		buffer.writeString(this._name);
		switch (this._type)
		{
			case 0:
				buffer.writeInt(this._objectId);
			case 1:
			default:
				break;
			case 2:
				buffer.writeInt(this._level);
				break;
			case 3:
				buffer.writeInt(this._classId);
		}
	}
}
