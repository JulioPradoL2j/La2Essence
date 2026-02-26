package org.l2jmobius.gameserver.network.serverpackets.commission;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExResponseCommissionDelete extends ServerPacket
{
	public static final ExResponseCommissionDelete SUCCEED = new ExResponseCommissionDelete(1);
	public static final ExResponseCommissionDelete FAILED = new ExResponseCommissionDelete(0);
	private final int _result;

	private ExResponseCommissionDelete(int result)
	{
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESPONSE_COMMISSION_DELETE.writeId(this, buffer);
		buffer.writeInt(this._result);
	}
}
