package org.l2jmobius.gameserver.network.serverpackets.pledgebonus;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.enums.ClanRewardType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPledgeBonusUpdate extends ServerPacket
{
	private final ClanRewardType _type;
	private final int _value;

	public ExPledgeBonusUpdate(ClanRewardType type, int value)
	{
		this._type = type;
		this._value = value;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_BONUS_UPDATE.writeId(this, buffer);
		buffer.writeByte(this._type.getClientId());
		buffer.writeInt(this._value);
	}
}
