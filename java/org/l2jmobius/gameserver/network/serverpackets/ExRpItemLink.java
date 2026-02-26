package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExRpItemLink extends AbstractItemPacket
{
	private final Item _item;

	public ExRpItemLink(Item item)
	{
		this._item = item;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RP_ITEMLINK.writeId(this, buffer);
		Player player = this._item.asPlayer();
		if (player != null && player.isOnline())
		{
			buffer.writeByte(1);
			buffer.writeInt(player.getObjectId());
		}
		else
		{
			buffer.writeByte(0);
			buffer.writeInt(0);
		}

		this.writeItem(this._item, buffer);
	}
}
