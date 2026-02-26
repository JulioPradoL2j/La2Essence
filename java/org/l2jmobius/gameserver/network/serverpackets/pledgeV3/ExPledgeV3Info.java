package org.l2jmobius.gameserver.network.serverpackets.pledgeV3;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPledgeV3Info extends ServerPacket
{
	private final int _points;
	private final int _rank;
	private final String _announce;
	private final boolean _isShowOnEnter;

	public ExPledgeV3Info(int points, int rank, String announce, boolean isShowOnEnter)
	{
		this._points = points;
		this._rank = rank;
		this._announce = announce;
		this._isShowOnEnter = isShowOnEnter;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_V3_INFO.writeId(this, buffer);
		buffer.writeInt(this._points);
		buffer.writeInt(this._rank);
		buffer.writeSizedString(this._announce);
		buffer.writeByte(this._isShowOnEnter);
	}
}
