package org.l2jmobius.gameserver.model.instancezone.conditions;

import java.util.List;

import org.l2jmobius.gameserver.model.StatSet;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.instancezone.InstanceTemplate;
import org.l2jmobius.gameserver.network.SystemMessageId;

public class ConditionGroupMin extends Condition
{
	public ConditionGroupMin(InstanceTemplate template, StatSet parameters, boolean onlyLeader, boolean showMessageAndHtml)
	{
		super(template, parameters, true, showMessageAndHtml);
		this.setSystemMessage(SystemMessageId.YOU_MUST_HAVE_A_MINIMUM_OF_S1_PEOPLE_TO_ENTER_THIS_INSTANCE_ZONE, (msg, _) -> msg.addInt(this.getLimit()));
	}

	@Override
	protected boolean test(Player player, Npc npc, List<Player> group)
	{
		return group.size() >= this.getLimit();
	}

	public int getLimit()
	{
		return this.getParameters().getInt("limit");
	}
}
