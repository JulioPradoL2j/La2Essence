package org.l2jmobius.gameserver.network.clientpackets.elementalspirits;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.elementalspirits.ElementalSpiritEvolutionInfo;

public class ExElementalSpiritEvolutionInfo extends ClientPacket
{
	private byte _id;

	@Override
	protected void readImpl()
	{
		this._id = this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ElementalSpiritEvolutionInfo(player, this._id));
		}
	}
}
