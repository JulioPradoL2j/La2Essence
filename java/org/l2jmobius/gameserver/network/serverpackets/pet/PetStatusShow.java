package org.l2jmobius.gameserver.network.serverpackets.pet;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Summon;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class PetStatusShow extends ServerPacket
{
	private final int _summonType;
	private final int _summonObjectId;

	public PetStatusShow(Summon summon)
	{
		this._summonType = summon.getSummonType();
		this._summonObjectId = summon.getObjectId();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PET_STATUS_SHOW.writeId(this, buffer);
		buffer.writeInt(this._summonType);
		buffer.writeInt(this._summonObjectId);
	}
}
