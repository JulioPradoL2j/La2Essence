package org.l2jmobius.gameserver.network.serverpackets.elementalspirits;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.ElementalSpirit;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.ElementalSpiritType;

public abstract class UpdateElementalSpiritPacket extends AbstractElementalSpiritPacket
{
	private final Player _player;
	private final byte _type;
	private final boolean _update;

	UpdateElementalSpiritPacket(Player player, byte type, boolean update)
	{
		this._player = player;
		this._type = type;
		this._update = update;
	}

	protected void writeUpdate(WritableBuffer buffer)
	{
		buffer.writeByte(this._update);
		buffer.writeByte(this._type);
		if (this._update)
		{
			ElementalSpirit spirit = this._player.getElementalSpirit(ElementalSpiritType.of(this._type));
			if (spirit == null)
			{
				return;
			}

			buffer.writeByte(this._type);
			this.writeSpiritInfo(buffer, spirit);
		}
	}
}
