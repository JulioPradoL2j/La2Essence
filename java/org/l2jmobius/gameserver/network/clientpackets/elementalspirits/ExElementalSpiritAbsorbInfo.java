package org.l2jmobius.gameserver.network.clientpackets.elementalspirits;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.elementalspirits.ElementalSpiritAbsorbInfo;

public class ExElementalSpiritAbsorbInfo extends ClientPacket
{
	private byte _type;

	@Override
	protected void readImpl()
	{
		this.readByte();
		this._type = this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ElementalSpiritAbsorbInfo(player, this._type));
		}
	}
}
