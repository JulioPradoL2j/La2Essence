package org.l2jmobius.gameserver.network.serverpackets.skillenchantextract;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExExtractSkillEnchant extends ServerPacket
{
	private final byte _result;
	private final int _skillId;
	private final int _level;
	private final int _subLevel;

	public ExExtractSkillEnchant(byte result, int skillId, int skillLevel, int skillSubLevel)
	{
		this._result = result;
		this._skillId = skillId;
		this._level = skillLevel;
		this._subLevel = skillSubLevel;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_EXTRACT_SKILL_ENCHANT.writeId(this, buffer);
		buffer.writeByte(this._result);
		buffer.writeInt(this._skillId);
		buffer.writeInt(this._level);
		buffer.writeInt(this._subLevel);
	}
}
