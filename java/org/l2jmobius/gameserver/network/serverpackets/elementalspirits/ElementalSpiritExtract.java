package org.l2jmobius.gameserver.network.serverpackets.elementalspirits;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ElementalSpiritExtract extends UpdateElementalSpiritPacket
{
	public ElementalSpiritExtract(Player player, byte type, boolean extracted)
	{
		super(player, type, extracted);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ELEMENTAL_SPIRIT_EXTRACT.writeId(this, buffer);
		this.writeUpdate(buffer);
	}
}
