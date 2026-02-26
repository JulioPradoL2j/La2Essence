package org.l2jmobius.gameserver.model.zone.type;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.Summon;
import org.l2jmobius.gameserver.model.zone.ZoneId;
import org.l2jmobius.gameserver.model.zone.ZoneType;

public class NoPvPZone extends ZoneType
{
	public NoPvPZone(int id)
	{
		super(id);
	}

	@Override
	protected void onEnter(Creature creature)
	{
		if (creature.isPlayer())
		{
			Player player = creature.asPlayer();
			if (player.getSiegeState() != 0 && GeneralConfig.PEACE_ZONE_MODE == 1)
			{
				return;
			}
		}

		if (GeneralConfig.PEACE_ZONE_MODE != 2)
		{
			creature.setInsideZone(ZoneId.NO_PVP, true);
		}

		if (creature.isPlayer())
		{
			creature.broadcastInfo();
		}
	}

	@Override
	protected void onExit(Creature creature)
	{
		if (GeneralConfig.PEACE_ZONE_MODE != 2)
		{
			creature.setInsideZone(ZoneId.NO_PVP, false);
		}

		if (creature.isPlayer() && !creature.isTeleporting())
		{
			creature.broadcastInfo();
		}
	}

	@Override
	public void setEnabled(boolean value)
	{
		super.setEnabled(value);
		if (value)
		{
			for (Player player : World.getInstance().getPlayers())
			{
				if (player != null && this.isInsideZone(player))
				{
					this.revalidateInZone(player);
					if (player.getPet() != null)
					{
						this.revalidateInZone(player.getPet());
					}

					for (Summon summon : player.getServitors().values())
					{
						this.revalidateInZone(summon);
					}
				}
			}
		}
		else
		{
			for (Creature creature : this.getCharactersInside())
			{
				if (creature != null)
				{
					this.removeCharacter(creature);
				}
			}
		}
	}
}
