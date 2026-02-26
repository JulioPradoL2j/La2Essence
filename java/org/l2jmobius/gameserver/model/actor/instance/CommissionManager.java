package org.l2jmobius.gameserver.model.actor.instance;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.creature.InstanceType;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;
import org.l2jmobius.gameserver.network.serverpackets.commission.ExShowCommission;

public class CommissionManager extends Npc
{
	public CommissionManager(NpcTemplate template)
	{
		super(template);
		this.setInstanceType(InstanceType.CommissionManager);
	}

	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		return attacker.isMonster() ? true : super.isAutoAttackable(attacker);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if (command.equalsIgnoreCase("show_commission"))
		{
			player.sendPacket(ExShowCommission.STATIC_PACKET);
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}
}
