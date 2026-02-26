package org.l2jmobius.gameserver.network.serverpackets.surveillance;

import java.util.LinkedList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.sql.CharInfoTable;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExUserWatcherTargetList extends ServerPacket
{
	private final List<ExUserWatcherTargetList.TargetInfo> _info = new LinkedList<>();

	public ExUserWatcherTargetList(Player player)
	{
		for (int objId : player.getSurveillanceList())
		{
			String name = CharInfoTable.getInstance().getNameById(objId);
			Player target = World.getInstance().getPlayer(objId);
			boolean online = false;
			int level = 0;
			int classId = 0;
			if (target != null)
			{
				online = true;
				level = target.getLevel();
				classId = target.getPlayerClass().getId();
			}
			else
			{
				level = CharInfoTable.getInstance().getLevelById(objId);
				classId = CharInfoTable.getInstance().getClassIdById(objId);
			}

			this._info.add(new ExUserWatcherTargetList.TargetInfo(name, online, level, classId));
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_USER_WATCHER_TARGET_LIST.writeId(this, buffer);
		buffer.writeInt(this._info.size());

		for (ExUserWatcherTargetList.TargetInfo info : this._info)
		{
			buffer.writeSizedString(info._name);
			buffer.writeInt(0);
			buffer.writeInt(info._level);
			buffer.writeInt(info._classId);
			buffer.writeByte(info._online ? 1 : 0);
		}
	}

	private static class TargetInfo
	{
		private final String _name;
		private final int _level;
		private final int _classId;
		private final boolean _online;

		public TargetInfo(String name, boolean online, int level, int classId)
		{
			this._name = name;
			this._online = online;
			this._level = level;
			this._classId = classId;
		}
	}
}
