package org.l2jmobius.gameserver.network.serverpackets.huntpass;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.HuntPass;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class HuntPassSimpleInfo extends ServerPacket
{
	private final HuntPass _huntPassInfo;

	public HuntPassSimpleInfo(Player player)
	{
		this._huntPassInfo = player.getHuntPass();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_L2PASS_SIMPLE_INFO.writeId(this, buffer);
		buffer.writeInt(1);
		buffer.writeByte(0);
		buffer.writeByte(1);
		buffer.writeByte(this._huntPassInfo.rewardAlert());
		buffer.writeInt(0);
	}
}
