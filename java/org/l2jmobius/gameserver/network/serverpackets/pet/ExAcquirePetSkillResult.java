package org.l2jmobius.gameserver.network.serverpackets.pet;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAcquirePetSkillResult extends ServerPacket
{
	private final int _skillId;
	private final int _skillLevel;
	private final boolean _success;

	public ExAcquirePetSkillResult(int skillId, int skillLevel, boolean success)
	{
		this._skillId = skillId;
		this._skillLevel = skillLevel;
		this._success = success;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ACQUIRE_PET_SKILL_RESULT.writeId(this, buffer);
		buffer.writeByte(this._success);
		buffer.writeInt(this._skillId);
		buffer.writeInt(this._skillLevel);
	}
}
