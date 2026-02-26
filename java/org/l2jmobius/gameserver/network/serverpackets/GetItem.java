package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class GetItem extends ServerPacket
{
	private final Item _item;
	private final int _playerId;

	public GetItem(Item item, int playerId)
	{
		this._item = item;
		this._playerId = playerId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.GET_ITEM.writeId(this, buffer);
		buffer.writeInt(this._playerId);
		buffer.writeInt(this._item.getObjectId());
		buffer.writeInt(this._item.getX());
		buffer.writeInt(this._item.getY());
		buffer.writeInt(this._item.getZ());
	}
}
