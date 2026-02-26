package org.l2jmobius.gameserver.network.serverpackets.balok;

import java.util.concurrent.TimeUnit;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.GlobalVariablesManager;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class BalrogWarHud extends ServerPacket
{
	private final int _state;
	private final int _stage;
	private final int _time;

	public BalrogWarHud(int state, int stage)
	{
		this._state = state;
		this._stage = stage;
		long remainTime = GlobalVariablesManager.getInstance().getLong("BALOK_REMAIN_TIME", 0L);
		long currentTime = System.currentTimeMillis();
		this._time = (int) TimeUnit.MILLISECONDS.toSeconds(remainTime - currentTime);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BALROGWAR_HUD.writeId(this, buffer);
		buffer.writeInt(this._state);
		buffer.writeInt(this._stage);
		buffer.writeInt(this._time);
	}
}
