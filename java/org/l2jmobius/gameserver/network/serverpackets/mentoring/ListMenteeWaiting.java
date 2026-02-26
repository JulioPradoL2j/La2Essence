package org.l2jmobius.gameserver.network.serverpackets.mentoring;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ListMenteeWaiting extends ServerPacket
{
	 
	private final List<Player> _possibleCandiates = new ArrayList<>();
	private final int _page;

	public ListMenteeWaiting(int page, int minLevel, int maxLevel)
	{
		this._page = page;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MENTEE_WAITING_LIST.writeId(this, buffer);
		buffer.writeInt(1);
		if (this._possibleCandiates.isEmpty())
		{
			buffer.writeInt(0);
			buffer.writeInt(0);
		}
		else
		{
			buffer.writeInt(this._possibleCandiates.size());
			buffer.writeInt(this._possibleCandiates.size() % 64);

			for (Player player : this._possibleCandiates)
			{
				if (1 <= 64 * this._page && 1 > 64 * (this._page - 1))
				{
					buffer.writeString(player.getName());
					buffer.writeInt(player.getActiveClass());
					buffer.writeInt(player.getLevel());
				}
			}
		}
	}
}
