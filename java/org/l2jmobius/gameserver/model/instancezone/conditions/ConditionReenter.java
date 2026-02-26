package org.l2jmobius.gameserver.model.instancezone.conditions;

import org.l2jmobius.gameserver.managers.InstanceManager;
import org.l2jmobius.gameserver.model.StatSet;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.instancezone.InstanceTemplate;
import org.l2jmobius.gameserver.network.SystemMessageId;

public class ConditionReenter extends Condition
{
	public ConditionReenter(InstanceTemplate template, StatSet parameters, boolean onlyLeader, boolean showMessageAndHtml)
	{
		super(template, parameters, onlyLeader, showMessageAndHtml);
		this.setSystemMessage(SystemMessageId.C1_CANNOT_ENTER_YET, (message, player) -> message.addString(player.getName()));
	}

	@Override
	protected boolean test(Player player, Npc npc)
	{
		int instanceId = this.getParameters().getInt("instanceId", this.getInstanceTemplate().getId());
		return System.currentTimeMillis() > InstanceManager.getInstance().getInstanceTime(player, instanceId);
	}
}
