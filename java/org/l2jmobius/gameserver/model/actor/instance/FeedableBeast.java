package org.l2jmobius.gameserver.model.actor.instance;

import org.l2jmobius.gameserver.model.actor.enums.creature.InstanceType;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;

public class FeedableBeast extends Monster
{
	public FeedableBeast(NpcTemplate template)
	{
		super(template);
		this.setInstanceType(InstanceType.FeedableBeast);
	}
}
