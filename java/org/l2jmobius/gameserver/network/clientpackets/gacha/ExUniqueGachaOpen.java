package org.l2jmobius.gameserver.network.clientpackets.gacha;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.gacha.UniqueGachaOpen;

public class ExUniqueGachaOpen extends ClientPacket
{
	private int _fullInfo;
	private int _openMode;

	@Override
	protected void readImpl()
	{
		this._fullInfo = this.readByte();
		this._openMode = this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new UniqueGachaOpen(player, this._fullInfo, this._openMode));
		}
	}
}
