package org.l2jmobius.gameserver.network.serverpackets.adenlab;

import java.util.List;
import java.util.Map;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAdenLabSpecialProb extends ServerPacket
{
	private final int _bossId;
	private final int _pageIndex;
	private final Map<Integer, List<Integer>> _pkAdenLabSpecialGradeProb;

	public ExAdenLabSpecialProb(int bossID, int slotID, Map<Integer, List<Integer>> pkAdenLabSpecialGradeProb)
	{
		this._bossId = bossID;
		this._pageIndex = slotID;
		this._pkAdenLabSpecialGradeProb = pkAdenLabSpecialGradeProb;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ADENLAB_SPECIAL_PROB.writeId(this, buffer);
		buffer.writeInt(this._bossId);
		buffer.writeInt(this._pageIndex);
		buffer.writeInt(this._pkAdenLabSpecialGradeProb.size());

		for (List<Integer> specialGradeProb : this._pkAdenLabSpecialGradeProb.values())
		{
			buffer.writeInt(specialGradeProb.size());

			for (int prob : specialGradeProb)
			{
				buffer.writeInt(prob);
			}
		}
	}
}
