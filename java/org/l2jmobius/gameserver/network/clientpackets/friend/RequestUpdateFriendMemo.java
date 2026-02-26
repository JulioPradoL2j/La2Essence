package org.l2jmobius.gameserver.network.clientpackets.friend;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.friend.ExFriendDetailInfo;

public class RequestUpdateFriendMemo extends ClientPacket
{
	private String _name;
	private String _memo;

	@Override
	protected void readImpl()
	{
		this._name = this.readString();
		this._memo = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.updateFriendMemo(this._name, this._memo);
			player.sendPacket(new ExFriendDetailInfo(player, this._name));
		}
	}
}
