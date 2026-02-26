package org.l2jmobius.gameserver.network.clientpackets.subjugation;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.PlayerPurgeHolder;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.subjugation.ExSubjugationGachaUI;

public class RequestSubjugationGachaUI extends ClientPacket
{
	private int _category;

	@Override
	protected void readImpl()
	{
		this._category = this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			PlayerPurgeHolder purgeHolder = player.getPurgePoints().get(this._category);
			player.sendPacket(new ExSubjugationGachaUI(this._category, purgeHolder != null ? purgeHolder.getKeys() : 0));
		}
	}
}
