package org.l2jmobius.gameserver.network.serverpackets.elementalspirits;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.ElementalSpirit;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

abstract class AbstractElementalSpiritPacket extends ServerPacket
{
	void writeSpiritInfo(WritableBuffer buffer, ElementalSpirit spirit)
	{
		buffer.writeByte(spirit.getStage());
		buffer.writeInt(spirit.getNpcId());
		buffer.writeLong(spirit.getExperience());
		buffer.writeLong(spirit.getExperienceToNextLevel());
		buffer.writeLong(spirit.getExperienceToNextLevel());
		buffer.writeInt(spirit.getLevel());
		buffer.writeInt(spirit.getMaxLevel());
		buffer.writeInt(spirit.getAvailableCharacteristicsPoints());
		buffer.writeInt(spirit.getAttackPoints());
		buffer.writeInt(spirit.getDefensePoints());
		buffer.writeInt(spirit.getCriticalRatePoints());
		buffer.writeInt(spirit.getCriticalDamagePoints());
		buffer.writeInt(spirit.getMaxCharacteristics());
		buffer.writeInt(spirit.getMaxCharacteristics());
		buffer.writeInt(spirit.getMaxCharacteristics());
		buffer.writeInt(spirit.getMaxCharacteristics());
		buffer.writeByte(1);

		for (int j = 0; j < 1; j++)
		{
			buffer.writeShort(2);
			buffer.writeLong(100L);
		}
	}
}
