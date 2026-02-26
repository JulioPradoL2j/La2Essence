package org.l2jmobius.gameserver.model.instancezone.conditions;

import org.l2jmobius.gameserver.model.StatSet;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.instancezone.InstanceTemplate;
import org.l2jmobius.gameserver.network.SystemMessageId;

public class ConditionDistance extends Condition
{
	public ConditionDistance(InstanceTemplate template, StatSet parameters, boolean onlyLeader, boolean showMessageAndHtml)
	{
		super(template, parameters, onlyLeader, showMessageAndHtml);
		this.setSystemMessage(SystemMessageId.C1_IS_TOO_FAR_FROM_THE_INSTANCE_ZONE_ENTRANCE, (message, player) -> message.addString(player.getName()));
	}

	@Override
	public boolean test(Player player, Npc npc)
	{
		int distance = this.getParameters().getInt("distance", 1000);
		return player.isInsideRadius3D(npc, distance);
	}
}
