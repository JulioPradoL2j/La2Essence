package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Summon;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPartyPetWindowAdd extends ServerPacket
{
	private final Summon _summon;

	public ExPartyPetWindowAdd(Summon summon)
	{
		this._summon = summon;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PARTY_PET_WINDOW_ADD.writeId(this, buffer);
		buffer.writeInt(this._summon.getObjectId());
		buffer.writeInt(this._summon.getTemplate().getDisplayId() + 1000000);
		buffer.writeByte(this._summon.getSummonType());
		buffer.writeInt(this._summon.getOwner().getObjectId());
		buffer.writeInt((int) this._summon.getCurrentHp());
		buffer.writeInt((int) this._summon.getMaxHp());
		buffer.writeInt((int) this._summon.getCurrentMp());
		buffer.writeInt(this._summon.getMaxMp());
	}
}
