package org.l2jmobius.gameserver.handler;

import java.util.logging.Logger;

import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.creature.InstanceType;

public interface IActionShiftHandler
{
	Logger LOGGER = Logger.getLogger(IActionShiftHandler.class.getName());

	boolean onAction(Player var1, WorldObject var2, boolean var3);

	InstanceType getInstanceType();
}
