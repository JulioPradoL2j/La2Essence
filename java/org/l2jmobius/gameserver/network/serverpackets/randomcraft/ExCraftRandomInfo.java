package org.l2jmobius.gameserver.network.serverpackets.randomcraft;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.holders.RandomCraftRewardItemHolder;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCraftRandomInfo extends ServerPacket
{
	private final List<RandomCraftRewardItemHolder> _rewards;

	public ExCraftRandomInfo(Player player)
	{
		this._rewards = player.getRandomCraft().getRewards();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CRAFT_RANDOM_INFO.writeId(this, buffer);
		int size = 5;
		buffer.writeInt(size);

		for (RandomCraftRewardItemHolder holder : this._rewards)
		{
			if (holder != null && holder.getItemId() != 0)
			{
				buffer.writeByte(holder.isLocked());
				buffer.writeInt(holder.getLockLeft());
				buffer.writeInt(holder.getItemId());
				buffer.writeLong(holder.getItemCount());
			}
			else
			{
				buffer.writeByte(0);
				buffer.writeInt(0);
				buffer.writeInt(0);
				buffer.writeLong(0L);
			}

			size--;
		}

		for (int i = size; i > 0; i--)
		{
			buffer.writeByte(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeLong(0L);
		}
	}
}
