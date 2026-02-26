package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExVitalityPointInfo extends ServerPacket
{
	private final int _vitalityPoints;

	public ExVitalityPointInfo(int vitPoints)
	{
		this._vitalityPoints = vitPoints;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_VITALITY_POINT_INFO.writeId(this, buffer);
		buffer.writeInt(this._vitalityPoints);
	}
}
