package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExEnchantSkillResult extends ServerPacket
{
	public static final ExEnchantSkillResult STATIC_PACKET_TRUE = new ExEnchantSkillResult(false);
	public static final ExEnchantSkillResult STATIC_PACKET_FALSE = new ExEnchantSkillResult(true);
	private final boolean _enchanted;

	public ExEnchantSkillResult(boolean enchanted)
	{
		this._enchanted = enchanted;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ENCHANT_SKILL_RESULT.writeId(this, buffer);
		buffer.writeInt(this._enchanted);
	}
}
