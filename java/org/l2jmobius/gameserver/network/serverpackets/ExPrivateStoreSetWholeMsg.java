package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPrivateStoreSetWholeMsg extends ServerPacket
{
	private final int _objectId;
	private final String _message;

	public ExPrivateStoreSetWholeMsg(Player player, String msg)
	{
		this._objectId = player.getObjectId();
		this._message = msg;
	}

	public ExPrivateStoreSetWholeMsg(int objectId, String message)
	{
		this._objectId = objectId;
		this._message = message;
	}

	public ExPrivateStoreSetWholeMsg(Player player)
	{
		this(player, player.getSellList().getTitle());
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PRIVATE_STORE_WHOLE_MSG.writeId(this, buffer);
		buffer.writeInt(this._objectId);
		buffer.writeString(this._message);
	}
}
