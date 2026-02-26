package org.l2jmobius.gameserver.network.serverpackets.subjugation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.holders.player.PlayerPurgeHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExSubjugationList extends ServerPacket
{
	private final List<Entry<Integer, PlayerPurgeHolder>> _playerHolder;

	public ExSubjugationList(Map<Integer, PlayerPurgeHolder> playerHolder)
	{
		this._playerHolder = playerHolder.entrySet().stream().filter(it -> it.getValue() != null).collect(Collectors.toList());
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SUBJUGATION_LIST.writeId(this, buffer);
		buffer.writeInt(this._playerHolder.size());

		for (Entry<Integer, PlayerPurgeHolder> integerPlayerPurgeHolderEntry : this._playerHolder)
		{
			buffer.writeInt(integerPlayerPurgeHolderEntry.getKey());
			buffer.writeInt(integerPlayerPurgeHolderEntry.getValue() != null ? integerPlayerPurgeHolderEntry.getValue().getPoints() : 0);
			buffer.writeInt(integerPlayerPurgeHolderEntry.getValue() != null ? integerPlayerPurgeHolderEntry.getValue().getKeys() : 0);
			buffer.writeInt(integerPlayerPurgeHolderEntry.getValue() != null ? integerPlayerPurgeHolderEntry.getValue().getRemainingKeys() : 40);
		}
	}
}
