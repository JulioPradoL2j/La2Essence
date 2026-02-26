package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PrivateStoreMsgSell extends ServerPacket
{
	private final int _objectId;
	private String _message;

	public PrivateStoreMsgSell(Player player)
	{
		this._objectId = player.getObjectId();
		if (player.getSellList() != null || player.isSellingBuffs())
		{
			this._message = player.getSellList().getTitle();
		}
	}

	public PrivateStoreMsgSell(int objectId, String message)
	{
		this._objectId = objectId;
		this._message = message;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PRIVATE_STORE_MSG.writeId(this, buffer);
		buffer.writeInt(this._objectId);
		buffer.writeString(this._message);
	}
}
