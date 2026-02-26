package org.l2jmobius.gameserver.network.serverpackets.penaltyitemdrop;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPenaltyItemInfo extends ServerPacket
{
	private final Player _player;

	public ExPenaltyItemInfo(Player player)
	{
		this._player = player;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PENALTY_ITEM_INFO.writeId(this, buffer);
		buffer.writeInt(this._player.getItemPenaltyList().size());
		buffer.writeInt(1);
	}
}
