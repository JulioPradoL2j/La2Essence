package org.l2jmobius.gameserver.network.serverpackets.adenlab;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAdenLabTranscendProb extends ServerPacket
{
	private final int _bossId;
	private final List<Integer> _probs;

	public ExAdenLabTranscendProb(int bossId, List<Integer> probabilities)
	{
		this._bossId = bossId;
		this._probs = probabilities;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ADENLAB_TRANSCEND_PROB.writeId(this, buffer);
		buffer.writeInt(this._bossId);
		buffer.writeInt(this._probs.size());

		for (int probability : this._probs)
		{
			buffer.writeInt(probability);
		}
	}
}
