package org.l2jmobius.gameserver.network.clientpackets.dailymission;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.dailymission.ExOneDayReceiveRewardList;

public class RequestTodoList extends ClientPacket
{
	private int _tab;
	protected boolean _showAllLevels;

	@Override
	protected void readImpl()
	{
		this._tab = this.readByte();
		this._showAllLevels = this.readByte() == 1;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			switch (this._tab)
			{
				case 9:
					player.sendPacket(new ExOneDayReceiveRewardList(player, true));
			}
		}
	}
}
