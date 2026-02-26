package org.l2jmobius.gameserver.network.serverpackets.subjugation;

import java.util.Collection;
import java.util.Map;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.SubjugationGacha;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExSubjugationGachaUI extends ServerPacket
{
	private final int _category;
	private final int _keyCount;

	public ExSubjugationGachaUI(int category, int keyCount)
	{
		this._category = category;
		this._keyCount = keyCount;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SUBJUGATION_GACHA_UI.writeId(this, buffer);
		buffer.writeInt(this._category);
		buffer.writeInt(this._keyCount);
		Map<Integer, Double> subjugationData = SubjugationGacha.getInstance().getSubjugation(this._category);
		if (subjugationData == null)
		{
			buffer.writeInt(0);
		}
		else
		{
			Collection<Double> values = subjugationData.values();
			buffer.writeInt(values.size());

			for (Double chance : values)
			{
				buffer.writeInt((int) (chance * 100.0));
			}
		}
	}
}
