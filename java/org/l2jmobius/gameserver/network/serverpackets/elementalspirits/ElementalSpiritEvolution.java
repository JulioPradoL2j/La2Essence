package org.l2jmobius.gameserver.network.serverpackets.elementalspirits;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ElementalSpiritEvolution extends UpdateElementalSpiritPacket
{
	public ElementalSpiritEvolution(Player player, byte type, boolean evolved)
	{
		super(player, type, evolved);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ELEMENTAL_SPIRIT_EVOLUTION.writeId(this, buffer);
		this.writeUpdate(buffer);
	}
}
