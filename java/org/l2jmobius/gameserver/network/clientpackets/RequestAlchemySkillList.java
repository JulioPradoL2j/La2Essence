package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.creature.Race;
import org.l2jmobius.gameserver.network.serverpackets.ExAlchemySkillList;

public class RequestAlchemySkillList extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && player.getRace() == Race.ERTHEIA)
		{
			player.sendPacket(new ExAlchemySkillList(player));
		}
	}
}
