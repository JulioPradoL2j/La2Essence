package org.l2jmobius.gameserver.network.clientpackets.friend;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.l2jmobius.commons.database.DatabaseFactory;
import org.l2jmobius.gameserver.data.sql.CharInfoTable;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.network.serverpackets.friend.FriendRemove;

public class RequestFriendDel extends ClientPacket
{
	private String _name;

	@Override
	protected void readImpl()
	{
		this._name = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			int id = CharInfoTable.getInstance().getIdByName(this._name);
			if (id == -1)
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.C1_IS_NOT_ON_YOUR_FRIEND_LIST);
				sm.addString(this._name);
				player.sendPacket(sm);
			}
			else if (!player.getFriendList().contains(id))
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.C1_IS_NOT_ON_YOUR_FRIEND_LIST);
				sm.addString(this._name);
				player.sendPacket(sm);
			}
			else
			{
				try (Connection con = DatabaseFactory.getConnection(); PreparedStatement statement = con.prepareStatement("DELETE FROM character_friends WHERE (charId=? AND friendId=?) OR (charId=? AND friendId=?)");)
				{
					statement.setInt(1, player.getObjectId());
					statement.setInt(2, id);
					statement.setInt(3, id);
					statement.setInt(4, player.getObjectId());
					statement.execute();
					SystemMessage sm = new SystemMessage(SystemMessageId.S1_HAS_BEEN_REMOVED_FROM_YOUR_FRIEND_LIST_2);
					sm.addString(this._name);
					player.sendPacket(sm);
					player.getFriendList().remove(id);
					player.sendPacket(new FriendRemove(this._name, 1));
					Player target = World.getInstance().getPlayer(this._name);
					if (target != null)
					{
						target.getFriendList().remove(player.getObjectId());
						target.sendPacket(new FriendRemove(player.getName(), 1));
					}

					CharInfoTable.getInstance().removeFriendMemo(player.getObjectId(), id);
				}
				catch (Exception var12)
				{
					PacketLogger.warning("Could not del friend objectid: " + var12.getMessage());
				}
			}
		}
	}
}
