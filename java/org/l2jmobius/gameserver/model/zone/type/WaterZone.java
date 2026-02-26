package org.l2jmobius.gameserver.model.zone.type;

import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.transform.Transform;
import org.l2jmobius.gameserver.model.zone.ZoneId;
import org.l2jmobius.gameserver.model.zone.ZoneType;
import org.l2jmobius.gameserver.network.serverpackets.FakePlayerInfo;
import org.l2jmobius.gameserver.network.serverpackets.NpcInfo;
import org.l2jmobius.gameserver.network.serverpackets.ServerObjectInfo;

public class WaterZone extends ZoneType
{
	public WaterZone(int id)
	{
		super(id);
	}

	@Override
	protected void onEnter(Creature creature)
	{
		if (!creature.isInsideZone(ZoneId.WATER))
		{
			creature.setInsideZone(ZoneId.WATER, true);
		}

		if (creature.isPlayer())
		{
			Player player = creature.asPlayer();
			Transform transform = player.getTransformation();
			if (transform != null && !transform.canSwim())
			{
				creature.stopTransformation(true);
			}
			else
			{
				player.broadcastUserInfo();
			}
		}
		else if (creature.isNpc())
		{
			World.getInstance().forEachVisibleObject(creature, Player.class, playerx -> {
				if (creature.isFakePlayer())
				{
					playerx.sendPacket(new FakePlayerInfo(creature.asNpc()));
				}
				else if (creature.getRunSpeed() == 0.0)
				{
					playerx.sendPacket(new ServerObjectInfo(creature.asNpc(), playerx));
				}
				else
				{
					playerx.sendPacket(new NpcInfo(creature.asNpc()));
				}
			});
		}
	}

	@Override
	protected void onExit(Creature creature)
	{
		if (creature.isInsideZone(ZoneId.WATER))
		{
			creature.setInsideZone(ZoneId.WATER, false);
		}

		if (creature.isPlayer())
		{
			Player player = creature.asPlayer();
			if (!player.isInsideZone(ZoneId.WATER))
			{
				player.stopWaterTask();
			}

			if (!player.isTeleporting())
			{
				player.broadcastUserInfo();
			}
		}
		else if (creature.isNpc())
		{
			World.getInstance().forEachVisibleObject(creature, Player.class, playerx -> {
				if (creature.isFakePlayer())
				{
					playerx.sendPacket(new FakePlayerInfo(creature.asNpc()));
				}
				else if (creature.getRunSpeed() == 0.0)
				{
					playerx.sendPacket(new ServerObjectInfo(creature.asNpc(), playerx));
				}
				else
				{
					playerx.sendPacket(new NpcInfo(creature.asNpc()));
				}
			});
		}
	}

	public int getWaterZ()
	{
		return this.getZone().getHighZ();
	}
}
