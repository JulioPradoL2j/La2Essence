package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Summon;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPartyPetWindowDelete extends ServerPacket
{
	private final Summon _summon;

	public ExPartyPetWindowDelete(Summon summon)
	{
		this._summon = summon;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PARTY_PET_WINDOW_DELETE.writeId(this, buffer);
		buffer.writeInt(this._summon.getObjectId());
		buffer.writeByte(this._summon.getSummonType());
		buffer.writeInt(this._summon.getOwner().getObjectId());
	}
}
