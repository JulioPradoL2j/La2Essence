package org.l2jmobius.gameserver.network.serverpackets.elementalspirits;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ElementalSpiritAbsorb extends UpdateElementalSpiritPacket
{
	public ElementalSpiritAbsorb(Player player, byte type, boolean absorbed)
	{
		super(player, type, absorbed);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ELEMENTAL_SPIRIT_ABSORB.writeId(this, buffer);
		this.writeUpdate(buffer);
	}
}
