package org.l2jmobius.gameserver.network.serverpackets.commission;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExResponseCommissionRegister extends ServerPacket
{
	public static final ExResponseCommissionRegister SUCCEED = new ExResponseCommissionRegister(1);
	public static final ExResponseCommissionRegister FAILED = new ExResponseCommissionRegister(0);
	private final int _result;

	private ExResponseCommissionRegister(int result)
	{
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESPONSE_COMMISSION_REGISTER.writeId(this, buffer);
		buffer.writeInt(this._result);
	}
}
