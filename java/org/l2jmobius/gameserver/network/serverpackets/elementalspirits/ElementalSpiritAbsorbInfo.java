package org.l2jmobius.gameserver.network.serverpackets.elementalspirits;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.ElementalSpirit;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.ElementalSpiritType;
import org.l2jmobius.gameserver.model.item.holders.ElementalSpiritAbsorbItemHolder;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ElementalSpiritAbsorbInfo extends ServerPacket
{
	private final Player _player;
	private final byte _type;

	public ElementalSpiritAbsorbInfo(Player player, byte type)
	{
		this._player = player;
		this._type = type;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ELEMENTAL_SPIRIT_ABSORB_INFO.writeId(this, buffer);
		ElementalSpirit spirit = this._player.getElementalSpirit(ElementalSpiritType.of(this._type));
		if (spirit == null)
		{
			buffer.writeByte(0);
			buffer.writeByte(0);
		}
		else
		{
			buffer.writeByte(1);
			buffer.writeByte(this._type);
			buffer.writeByte(spirit.getStage());
			buffer.writeLong(spirit.getExperience());
			buffer.writeLong(spirit.getExperienceToNextLevel());
			buffer.writeLong(spirit.getExperienceToNextLevel());
			buffer.writeInt(spirit.getLevel());
			buffer.writeInt(spirit.getMaxLevel());
			List<ElementalSpiritAbsorbItemHolder> absorbItems = spirit.getAbsorbItems();
			buffer.writeInt(absorbItems.size());

			for (ElementalSpiritAbsorbItemHolder absorbItem : absorbItems)
			{
				buffer.writeInt(absorbItem.getId());
				Item item = this._player.getInventory().getItemByItemId(absorbItem.getId());
				int itemCount = item != null ? (int) item.getCount() : 0;
				buffer.writeInt(itemCount);
				buffer.writeInt(absorbItem.getExperience());
			}
		}
	}
}
