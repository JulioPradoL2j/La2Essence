package org.l2jmobius.gameserver.network.serverpackets.olympiad;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExOlympiadMatchInfo extends ServerPacket
{
	private final String _name1;
	private final String _name2;
	private final int _wins1;
	private final int _wins2;
	private final int _round;

	public ExOlympiadMatchInfo(String name1, String name2, int wins1, int wins2, int round)
	{
		this._name1 = String.format("%1$-23s", name1);
		this._name2 = String.format("%1$-23s", name2);
		this._wins1 = wins1;
		this._wins2 = wins2;
		this._round = round;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_OLYMPIAD_MATCH_INFO.writeId(this, buffer);
		buffer.writeString(this._name2);
		buffer.writeInt(this._wins2);
		buffer.writeString(this._name1);
		buffer.writeInt(this._wins1);
		buffer.writeInt(this._round);
		buffer.writeInt(100);
	}
}
