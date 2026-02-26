package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.sql.CrestTable;
import org.l2jmobius.gameserver.model.Crest;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class AllyCrest extends ServerPacket
{
	private final int _clanId;
	private final int _crestId;
	private final byte[] _data;

	public AllyCrest(int crestId, int clanId)
	{
		this._crestId = crestId;
		this._clanId = clanId;
		Crest crest = CrestTable.getInstance().getCrest(crestId);
		this._data = crest != null ? crest.getData() : null;
	}

	public AllyCrest(int crestId, int clanId, byte[] data)
	{
		this._crestId = crestId;
		this._clanId = clanId;
		this._data = data;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.ALLIANCE_CREST.writeId(this, buffer);
		buffer.writeInt(this._crestId);
		buffer.writeInt(this._clanId);
		if (this._data != null)
		{
			buffer.writeInt(this._data.length);
			buffer.writeInt(this._data.length);
			buffer.writeBytes(this._data);
		}
		else
		{
			buffer.writeInt(0);
		}
	}
}
