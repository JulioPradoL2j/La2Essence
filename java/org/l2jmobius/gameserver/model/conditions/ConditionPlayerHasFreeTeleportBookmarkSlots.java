package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.SystemMessageId;

public class ConditionPlayerHasFreeTeleportBookmarkSlots extends Condition
{
	private final int _teleportBookmarkSlots;

	public ConditionPlayerHasFreeTeleportBookmarkSlots(int teleportBookmarkSlots)
	{
		this._teleportBookmarkSlots = teleportBookmarkSlots;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		Player player = effector.asPlayer();
		if (player == null)
		{
			return false;
		}
		else if (player.getBookMarkSlot() + this._teleportBookmarkSlots > 18)
		{
			player.sendPacket(SystemMessageId.YOU_HAVE_REACHED_THE_MAXIMUM_NUMBER_OF_MY_TELEPORT_SLOTS_OR_USE_CONDITIONS_ARE_NOT_OBSERVED);
			return false;
		}
		else
		{
			return true;
		}
	}
}
