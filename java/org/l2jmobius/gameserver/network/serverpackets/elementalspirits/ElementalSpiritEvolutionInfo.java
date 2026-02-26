package org.l2jmobius.gameserver.network.serverpackets.elementalspirits;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.ElementalSpirit;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.ElementalSpiritType;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ElementalSpiritEvolutionInfo extends ServerPacket
{
	private final Player _player;
	private final byte _type;

	public ElementalSpiritEvolutionInfo(Player player, byte type)
	{
		this._player = player;
		this._type = type;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ELEMENTAL_SPIRIT_EVOLUTION_INFO.writeId(this, buffer);
		ElementalSpirit spirit = this._player.getElementalSpirit(ElementalSpiritType.of(this._type));
		if (spirit == null)
		{
			buffer.writeByte(0);
			buffer.writeInt(0);
		}
		else
		{
			buffer.writeByte(this._type);
			buffer.writeInt(spirit.getNpcId());
			buffer.writeInt(1);
			buffer.writeInt(spirit.getStage());
			buffer.writeDouble(100.0);
			List<ItemHolder> items = spirit.getItemsToEvolve();
			buffer.writeInt(items.size());

			for (ItemHolder item : items)
			{
				buffer.writeInt(item.getId());
				buffer.writeLong(item.getCount());
			}
		}
	}
}
