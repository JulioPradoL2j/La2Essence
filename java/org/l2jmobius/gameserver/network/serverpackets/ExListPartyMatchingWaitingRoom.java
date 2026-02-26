package org.l2jmobius.gameserver.network.serverpackets;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.InstanceManager;
import org.l2jmobius.gameserver.managers.MatchingRoomManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.PlayerClass;
import org.l2jmobius.gameserver.model.instancezone.Instance;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExListPartyMatchingWaitingRoom extends ServerPacket
{
	 
	private final int _size;
	private final List<Player> _players = new LinkedList<>();

	public ExListPartyMatchingWaitingRoom(int page, int minLevel, int maxLevel, List<PlayerClass> classIds, String query)
	{
		List<Player> players = MatchingRoomManager.getInstance().getPlayerInWaitingList(minLevel, maxLevel, classIds, query);
		this._size = players.size();
		int startIndex = (page - 1) * 64;
		int chunkSize = this._size - startIndex;
		if (chunkSize > 64)
		{
			chunkSize = 64;
		}

		for (int i = startIndex; i < startIndex + chunkSize; i++)
		{
			this._players.add(players.get(i));
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_LIST_PARTY_MATCHING_WAITING_ROOM.writeId(this, buffer);
		buffer.writeInt(this._size);
		buffer.writeInt(this._players.size());

		for (Player player : this._players)
		{
			buffer.writeString(player.getName());
			buffer.writeInt(player.getPlayerClass().getId());
			buffer.writeInt(player.getLevel());
			Instance instance = InstanceManager.getInstance().getPlayerInstance(player, false);
			buffer.writeInt(instance != null && instance.getTemplateId() >= 0 ? instance.getTemplateId() : -1);
			Map<Integer, Long> instanceTimes = InstanceManager.getInstance().getAllInstanceTimes(player);
			buffer.writeInt(instanceTimes.size());

			for (Entry<Integer, Long> entry : instanceTimes.entrySet())
			{
				long instanceTime = TimeUnit.MILLISECONDS.toSeconds(entry.getValue() - System.currentTimeMillis());
				buffer.writeInt(entry.getKey());
				buffer.writeInt((int) instanceTime);
			}
		}
	}
}
