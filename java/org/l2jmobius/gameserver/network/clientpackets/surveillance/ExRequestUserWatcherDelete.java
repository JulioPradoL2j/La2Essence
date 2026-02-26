package org.l2jmobius.gameserver.network.clientpackets.surveillance;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.l2jmobius.commons.database.DatabaseFactory;
import org.l2jmobius.gameserver.data.sql.CharInfoTable;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.RelationChanged;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.network.serverpackets.surveillance.ExUserWatcherTargetList;

public class ExRequestUserWatcherDelete extends ClientPacket
{
	private String _name;

	@Override
	protected void readImpl()
	{
		this._name = this.readSizedString();
		this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			int targetId = CharInfoTable.getInstance().getIdByName(this._name);
			if (targetId == -1)
			{
				player.sendPacket(SystemMessageId.THAT_CHARACTER_DOES_NOT_EXIST);
			}
			else if (!player.getSurveillanceList().contains(targetId))
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.C1_IS_NOT_ON_YOUR_FRIEND_LIST);
				sm.addString(this._name);
				player.sendPacket(sm);
			}
			else
			{
				try (Connection con = DatabaseFactory.getConnection(); PreparedStatement statement = con.prepareStatement("DELETE FROM character_surveillances WHERE (charId=? AND targetId=?)");)
				{
					statement.setInt(1, player.getObjectId());
					statement.setInt(2, targetId);
					statement.execute();
				}
				catch (Exception var11)
				{
					PacketLogger.warning("ExRequestUserWatcherDelete: Could not add surveillance objectid: " + var11.getMessage());
				}

				SystemMessage sm = new SystemMessage(SystemMessageId.YOU_VE_REMOVED_C1_FROM_YOUR_SURVEILLANCE_LIST);
				sm.addString(this._name);
				player.sendPacket(sm);
				player.getSurveillanceList().remove(targetId);
				player.sendPacket(new ExUserWatcherTargetList(player));
				Player target = World.getInstance().getPlayer(targetId);
				if (target != null && target.isVisibleFor(player))
				{
					player.sendPacket(new RelationChanged());
				}
			}
		}
	}
}
