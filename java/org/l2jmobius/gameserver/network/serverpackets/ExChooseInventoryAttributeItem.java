package org.l2jmobius.gameserver.network.serverpackets;

import java.util.HashSet;
import java.util.Set;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.ElementalAttributeData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.creature.AttributeType;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExChooseInventoryAttributeItem extends ServerPacket
{
	private final int _itemId;
	private final long _count;
	private final AttributeType _atribute;
	private final int _level;
	private final Set<Integer> _items = new HashSet<>();

	public ExChooseInventoryAttributeItem(Player player, Item stone)
	{
		this._itemId = stone.getDisplayId();
		this._count = stone.getCount();
		this._atribute = ElementalAttributeData.getInstance().getItemElement(this._itemId);
		if (this._atribute == AttributeType.NONE)
		{
			throw new IllegalArgumentException("Undefined Atribute item: " + stone);
		}
		this._level = ElementalAttributeData.getInstance().getMaxElementLevel(this._itemId);

		for (Item item : player.getInventory().getItems())
		{
			if (item.isElementable())
			{
				this._items.add(item.getObjectId());
			}
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHOOSE_INVENTORY_ATTRIBUTE_ITEM.writeId(this, buffer);
		buffer.writeInt(this._itemId);
		buffer.writeLong(this._count);
		buffer.writeInt(this._atribute == AttributeType.FIRE);
		buffer.writeInt(this._atribute == AttributeType.WATER);
		buffer.writeInt(this._atribute == AttributeType.WIND);
		buffer.writeInt(this._atribute == AttributeType.EARTH);
		buffer.writeInt(this._atribute == AttributeType.HOLY);
		buffer.writeInt(this._atribute == AttributeType.DARK);
		buffer.writeInt(this._level);
		buffer.writeInt(this._items.size());
		this._items.forEach(buffer::writeInt);
	}
}
