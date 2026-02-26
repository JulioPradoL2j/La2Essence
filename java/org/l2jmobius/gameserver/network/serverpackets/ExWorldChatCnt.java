package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.config.VipSystemConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExWorldChatCnt extends ServerPacket
{
	private final int _points;

	public ExWorldChatCnt(Player player)
	{
		this._points = player.getLevel() >= GeneralConfig.WORLD_CHAT_MIN_LEVEL && (!VipSystemConfig.VIP_SYSTEM_ENABLED || player.getVipTier() > 0) ? Math.max(player.getWorldChatPoints() - player.getWorldChatUsed(), 0) : 0;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_WORLDCHAT_CNT.writeId(this, buffer);
		buffer.writeInt(this._points);
	}
}
