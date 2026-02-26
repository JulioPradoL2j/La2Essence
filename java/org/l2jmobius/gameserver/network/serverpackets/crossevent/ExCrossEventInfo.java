package org.l2jmobius.gameserver.network.serverpackets.crossevent;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.events.CrossEventManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.CrossEventHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCrossEventInfo extends ServerPacket
{
	private final Player _player;
	private final int _availableCoupons;
	private final int _dailyResets;
	private final int _endTime;
	private final int _advancedRewards;
	private final int _resetCount;

	public ExCrossEventInfo(Player player)
	{
		this._player = player;
		this._advancedRewards = player.getCrossRewardsCount();
		this._resetCount = player.getPlayerResetCount();
		CrossEventManager manager = CrossEventManager.getInstance();
		this._availableCoupons = manager.getGameTickets(player);
		this._dailyResets = manager.getDailyResets();
		this._endTime = manager.getEndTime();
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CROSS_EVENT_INFO.writeId(this, buffer);
		buffer.writeByte(1);
		buffer.writeInt(16);

		for (int i = 0; i < 16; i++)
		{
			buffer.writeLong(1L);
			buffer.writeByte(this.checkCell(i));
		}

		buffer.writeInt(this._availableCoupons);
		buffer.writeInt(this._advancedRewards);
		buffer.writeInt(this._resetCount);
		buffer.writeInt(this._dailyResets);
		buffer.writeLong(this._endTime);
	}

	private boolean checkCell(int cellNumber)
	{
		for (CrossEventHolder cells : this._player.getCrossEventCells())
		{
			if (cells.cellId() == cellNumber)
			{
				return true;
			}
		}

		return false;
	}
}
