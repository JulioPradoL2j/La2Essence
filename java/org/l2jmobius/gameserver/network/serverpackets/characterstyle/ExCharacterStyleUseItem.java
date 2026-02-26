package org.l2jmobius.gameserver.network.serverpackets.characterstyle;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.enums.CharacterStyleCategoryType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCharacterStyleUseItem extends ServerPacket
{
	final CharacterStyleCategoryType _type;
	final int _styleId;

	public ExCharacterStyleUseItem(CharacterStyleCategoryType type, int styleId)
	{
		this._type = type;
		this._styleId = styleId;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHARACTER_STYLE_USE_ITEM.writeId(this, buffer);
		buffer.writeInt(this._type.getClientId());
		buffer.writeInt(this._styleId);
	}
}
