package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.MailManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExUnReadMailCount extends ServerPacket
{
	private final int _mailUnreadCount;

	public ExUnReadMailCount(Player player)
	{
		this._mailUnreadCount = (int) MailManager.getInstance().getUnreadCount(player);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_UNREADMAIL_COUNT.writeId(this, buffer);
		buffer.writeInt(this._mailUnreadCount);
	}
}
