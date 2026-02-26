package org.l2jmobius.gameserver.network.serverpackets.elementalspirits;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.ElementalSpiritType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExElementalSpiritAttackType extends ServerPacket
{
	private final Player _player;

	public ExElementalSpiritAttackType(Player player)
	{
		this._player = player;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ELEMENTAL_SPIRIT_ATTACK_TYPE.writeId(this, buffer);
		byte elementalId = this._player.getActiveElementalSpiritType();
		if (elementalId == ElementalSpiritType.WIND.getId())
		{
			buffer.writeByte(4);
		}
		else if (elementalId == ElementalSpiritType.EARTH.getId())
		{
			buffer.writeByte(8);
		}
		else
		{
			buffer.writeByte(elementalId);
		}
	}
}
