package org.l2jmobius.gameserver.network.serverpackets.payback;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.events.PaybackManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.holders.ItemChanceHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class PaybackList extends ServerPacket
{
	private final Player _player;
	private final int _eventID;

	public PaybackList(Player player, int EventID)
	{
		this._player = player;
		this._eventID = EventID;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		PaybackManager manager = PaybackManager.getInstance();
		List<Integer> rewardStatus = manager.getPlayerMissionProgress(this._player.getObjectId());
		if (rewardStatus != null && !rewardStatus.isEmpty())
		{
			ServerPackets.EX_PAYBACK_LIST.writeId(this, buffer);
			buffer.writeInt(manager.getRewards().size());

			for (int id : manager.getRewards().keySet())
			{
				buffer.writeInt(manager.getRewards().get(id).getRewards().size());

				for (ItemChanceHolder reward : manager.getRewards().get(id).getRewards())
				{
					buffer.writeInt(reward.getId());
					buffer.writeInt(Math.toIntExact(reward.getCount()));
				}

				buffer.writeByte(id);
				buffer.writeInt(Math.toIntExact(manager.getRewards().get(id).getCount()));
				buffer.writeByte(rewardStatus.get(id - 1));
			}

			buffer.writeByte(this._eventID);
			buffer.writeInt(Math.toIntExact(manager.getEndTime() / 1000L));
			buffer.writeInt(manager.getCoinID());
			buffer.writeInt((int) manager.getPlayerConsumedProgress(this._player.getObjectId()));
		}
	}
}
