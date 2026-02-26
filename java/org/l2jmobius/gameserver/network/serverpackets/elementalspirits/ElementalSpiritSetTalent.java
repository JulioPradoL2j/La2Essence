package org.l2jmobius.gameserver.network.serverpackets.elementalspirits;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ElementalSpiritSetTalent extends UpdateElementalSpiritPacket
{
	public ElementalSpiritSetTalent(Player player, byte type, boolean result)
	{
		super(player, type, result);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ELEMENTAL_SPIRIT_SET_TALENT.writeId(this, buffer);
		this.writeUpdate(buffer);
	}
}
