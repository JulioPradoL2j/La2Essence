package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.SystemMessageId;

public class ExAcquireSkillResult extends ServerPacket
{
	private final int _skillId;
	private final int _skillLevel;
	private final boolean _success;
	private final SystemMessageId _message;

	public ExAcquireSkillResult(int skillId, int skillLevel, boolean success, SystemMessageId message)
	{
		this._skillId = skillId;
		this._skillLevel = skillLevel;
		this._success = success;
		this._message = message;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ACQUIRE_SKILL_RESULT.writeId(this, buffer);
		buffer.writeInt(this._skillId);
		buffer.writeInt(this._skillLevel);
		buffer.writeByte(!this._success);
		buffer.writeInt(this._message.getId());
	}
}
