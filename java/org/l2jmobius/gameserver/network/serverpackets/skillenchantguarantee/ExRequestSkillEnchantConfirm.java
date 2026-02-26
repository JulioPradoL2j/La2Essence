package org.l2jmobius.gameserver.network.serverpackets.skillenchantguarantee;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRequestSkillEnchantConfirm extends ServerPacket
{
	private final byte _result;
	private final int _skillId;
	private final int _skillLevel;
	private final int _skillSubLevel;

	public ExRequestSkillEnchantConfirm(byte result, int skillId, int skillLevel, int skillSubLevel)
	{
		this._result = result;
		this._skillId = skillId;
		this._skillLevel = skillLevel;
		this._skillSubLevel = skillSubLevel;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_REQUEST_SKILL_ENCHANT_CONFIRM.writeId(this, buffer);
		buffer.writeByte(this._result);
		buffer.writeInt(this._skillId);
		buffer.writeInt(this._skillLevel);
		buffer.writeInt(this._skillSubLevel);
	}
}
