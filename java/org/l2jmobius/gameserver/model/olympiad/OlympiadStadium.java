package org.l2jmobius.gameserver.model.olympiad;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.managers.InstanceManager;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.Spawn;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Door;
import org.l2jmobius.gameserver.model.instancezone.Instance;
import org.l2jmobius.gameserver.model.zone.ZoneId;
import org.l2jmobius.gameserver.model.zone.type.OlympiadStadiumZone;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.network.serverpackets.olympiad.ExOlympiadMatchEnd;
import org.l2jmobius.gameserver.network.serverpackets.olympiad.ExOlympiadUserInfo;

public class OlympiadStadium
{
	private static final Logger LOGGER = Logger.getLogger(OlympiadStadium.class.getName());
	private final OlympiadStadiumZone _zone;
	private final int _stadiumId;
	private final Instance _instance;
	private final List<Spawn> _buffers;
	private OlympiadGameTask _task = null;

	protected OlympiadStadium(OlympiadStadiumZone olyzone, int stadiumId)
	{
		this._zone = olyzone;
		this._stadiumId = stadiumId;
		this._instance = InstanceManager.getInstance().createInstance(olyzone.getInstanceTemplateId(), null);
		this._buffers = this._instance.getNpcs().stream().map(Npc::getSpawn).collect(Collectors.toList());
		this._buffers.stream().map(Spawn::getLastSpawn).forEach(Npc::deleteMe);
	}

	public OlympiadStadiumZone getZone()
	{
		return this._zone;
	}

	public void registerTask(OlympiadGameTask task)
	{
		this._task = task;
	}

	public OlympiadGameTask getTask()
	{
		return this._task;
	}

	public Instance getInstance()
	{
		return this._instance;
	}

	public void openDoors()
	{
		this._instance.getDoors().forEach(Door::openMe);
	}

	public void closeDoors()
	{
		this._instance.getDoors().forEach(Door::closeMe);
	}

	public void spawnBuffers()
	{
		this._buffers.forEach(spawn -> spawn.doSpawn(false));
	}

	public void deleteBuffers()
	{
		this._buffers.stream().map(Spawn::getLastSpawn).filter(Objects::nonNull).forEach(Npc::deleteMe);
	}

	public void broadcastStatusUpdate(Player player)
	{
		ExOlympiadUserInfo packet = new ExOlympiadUserInfo(player);

		for (Player target : this._instance.getPlayers())
		{
			if (target.inObserverMode() || target.getOlympiadSide() != player.getOlympiadSide())
			{
				target.sendPacket(packet);
			}
		}
	}

	public void broadcastPacket(ServerPacket packet)
	{
		this._instance.broadcastPacket(packet);
	}

	public void broadcastPacketToObservers(ServerPacket packet)
	{
		for (Player target : this._instance.getPlayers())
		{
			if (target.inObserverMode())
			{
				target.sendPacket(packet);
			}
		}
	}

	public void updateZoneStatusForCharactersInside()
	{
		if (this._task != null)
		{
			boolean battleStarted = this._task.isBattleStarted();
			SystemMessage sm;
			if (battleStarted)
			{
				sm = new SystemMessage(SystemMessageId.YOU_HAVE_ENTERED_A_COMBAT_ZONE);
			}
			else
			{
				sm = new SystemMessage(SystemMessageId.YOU_HAVE_LEFT_A_COMBAT_ZONE);
			}

			for (Player player : this._instance.getPlayers())
			{
				if (player.inObserverMode())
				{
					return;
				}

				if (battleStarted)
				{
					player.setInsideZone(ZoneId.PVP, true);
					player.sendPacket(sm);
				}
				else
				{
					player.setInsideZone(ZoneId.PVP, false);
					player.sendPacket(sm);
					player.sendPacket(ExOlympiadMatchEnd.STATIC_PACKET);
				}
			}
		}
	}

	public void updateZoneInfoForObservers()
	{
		if (this._task != null)
		{
			for (Player player : this._instance.getPlayers())
			{
				if (!player.inObserverMode())
				{
					return;
				}

				List<Location> spectatorSpawns = this.getZone().getSpectatorSpawns();
				if (spectatorSpawns.isEmpty())
				{
					LOGGER.warning(this.getClass().getSimpleName() + ": Zone: " + this.getZone() + " doesn't have specatator spawns defined!");
					return;
				}

				Location loc = spectatorSpawns.get(Rnd.get(spectatorSpawns.size()));
				player.enterOlympiadObserverMode(loc, this._stadiumId);
				this._task.getGame().sendOlympiadInfo(player);
			}
		}
	}
}
