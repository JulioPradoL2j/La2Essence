package org.l2jmobius.gameserver.network.serverpackets.pk;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPkPenaltyList extends ServerPacket
{
	private final int _lastPkTime;
	private final List<ExPkPenaltyList.PlayerHolder> _players = new LinkedList<>();

	public ExPkPenaltyList()
	{
		this._lastPkTime = World.getInstance().getLastPkTime();

		for (Player player : World.getInstance().getPkPlayers())
		{
			this._players.add(new ExPkPenaltyList.PlayerHolder(player.getObjectId(), String.format("%1$-23s", player.getName()), player.getLevel(), player.getPlayerClass().getId()));
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PK_PENALTY_LIST.writeId(this, buffer);
		buffer.writeInt(this._lastPkTime);
		buffer.writeInt(this._players.size());

		for (ExPkPenaltyList.PlayerHolder holder : this._players)
		{
			buffer.writeInt(holder.getObjectId());
			buffer.writeString(holder.getName());
			buffer.writeInt(holder.getLevel());
			buffer.writeInt(holder.getClassId());
		}
	}

	private class PlayerHolder
	{
		private final int _objectId;
		private final String _name;
		private final int _level;
		private final int _classId;

		public PlayerHolder(int objectId, String name, int level, int classId)
		{
			Objects.requireNonNull(ExPkPenaltyList.this);
			super();
			this._objectId = objectId;
			this._name = name;
			this._level = level;
			this._classId = classId;
		}

		public int getObjectId()
		{
			return this._objectId;
		}

		public String getName()
		{
			return this._name;
		}

		public int getLevel()
		{
			return this._level;
		}

		public int getClassId()
		{
			return this._classId;
		}
	}
}
