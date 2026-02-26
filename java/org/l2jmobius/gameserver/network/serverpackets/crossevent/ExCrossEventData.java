package org.l2jmobius.gameserver.network.serverpackets.crossevent;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.events.CrossEventManager;
import org.l2jmobius.gameserver.model.actor.holders.player.CrossEventAdvancedRewardHolder;
import org.l2jmobius.gameserver.model.actor.holders.player.CrossEventRegularRewardHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCrossEventData extends ServerPacket
{
	private final List<CrossEventRegularRewardHolder> _cellRewards;
	private final List<CrossEventAdvancedRewardHolder> _advancedRewards;
	private final long _resetCostAmount;
	private final int _displayId;

	public ExCrossEventData()
	{
		CrossEventManager manager = CrossEventManager.getInstance();
		this._cellRewards = manager.getRegularRewardsList();
		this._advancedRewards = manager.getAdvancedRewardList();
		this._resetCostAmount = manager.getResetCostAmount();
		this._displayId = manager.getDisplayId();
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CROSS_EVENT_DATA.writeId(this, buffer);
		buffer.writeInt(this._cellRewards.size());

		for (CrossEventRegularRewardHolder item : this._cellRewards)
		{
			buffer.writeInt(item.cellReward());
			buffer.writeLong(item.cellAmount());
		}

		buffer.writeInt(this._advancedRewards.size());

		for (CrossEventAdvancedRewardHolder reward : this._advancedRewards)
		{
			buffer.writeInt(reward.getItemId());
			buffer.writeLong(reward.getCount());
		}

		buffer.writeByte(0);
		buffer.writeByte(1);
		buffer.writeInt(57);
		buffer.writeLong(this._resetCostAmount);
		buffer.writeInt(this._displayId);
	}
}
