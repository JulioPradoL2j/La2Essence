package org.l2jmobius.gameserver.network.serverpackets.penaltyitemdrop;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPenaltyItemDrop extends ServerPacket
{
	private final Location _dropLoc;
	private final int _itemId;

	public ExPenaltyItemDrop(Location dropLoc, int itemId)
	{
		this._dropLoc = dropLoc;
		this._itemId = itemId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PENALTY_ITEM_DROP.writeId(this, buffer);
		buffer.writeInt(this._dropLoc.getX());
		buffer.writeInt(this._dropLoc.getY());
		buffer.writeInt(this._dropLoc.getZ());
		buffer.writeInt(this._itemId);
	}
}
