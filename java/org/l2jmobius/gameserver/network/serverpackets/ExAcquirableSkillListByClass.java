package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.SkillLearn;
import org.l2jmobius.gameserver.model.skill.enums.AcquireSkillType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExAcquirableSkillListByClass extends ServerPacket
{
	private final Collection<SkillLearn> _learnable;
	private final AcquireSkillType _type;

	public ExAcquirableSkillListByClass(Collection<SkillLearn> learnable, AcquireSkillType type)
	{
		this._learnable = learnable;
		this._type = type;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ACQUIRABLE_SKILL_LIST_BY_CLASS.writeId(this, buffer);
		buffer.writeShort(this._type.getId());
		buffer.writeShort(this._learnable.size());

		for (SkillLearn skill : this._learnable)
		{
			buffer.writeInt(skill.getSkillId());
			buffer.writeShort(skill.getSkillLevel());
			buffer.writeShort(skill.getSkillLevel());
			buffer.writeByte(skill.getGetLevel());
			buffer.writeLong(skill.getLevelUpSp());
			buffer.writeByte(skill.getRequiredItems().size());
			if (this._type == AcquireSkillType.SUBPLEDGE)
			{
				buffer.writeShort(0);
			}
		}
	}
}
