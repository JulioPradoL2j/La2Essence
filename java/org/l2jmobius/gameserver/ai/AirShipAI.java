package org.l2jmobius.gameserver.ai;

import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.AirShip;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.serverpackets.ExMoveToLocationAirShip;
import org.l2jmobius.gameserver.network.serverpackets.ExStopMoveAirShip;

public class AirShipAI extends CreatureAI
{
	public AirShipAI(AirShip airShip)
	{
		super(airShip);
	}

	@Override
	protected void moveTo(int x, int y, int z)
	{
		if (!this._actor.isMovementDisabled())
		{
			this._actor.moveToLocation(x, y, z, 0);
			this._actor.broadcastPacket(new ExMoveToLocationAirShip(this.getActor()));
		}
	}

	@Override
	public void clientStopMoving(Location loc)
	{
		if (this._actor.isMoving())
		{
			this._actor.stopMove(loc);
			this._actor.broadcastPacket(new ExStopMoveAirShip(this.getActor()));
		}
		else
		{
			if (loc != null)
			{
				this._actor.broadcastPacket(new ExStopMoveAirShip(this.getActor()));
			}
		}
	}

	@Override
	public void describeStateToPlayer(Player player)
	{
		if (this._actor.isMoving())
		{
			player.sendPacket(new ExMoveToLocationAirShip(this.getActor()));
		}
	}

	@Override
	protected void onIntentionAttack(Creature target)
	{
	}

	@Override
	protected void onIntentionCast(Skill skill, WorldObject target, Item item, boolean forceUse, boolean dontMove)
	{
	}

	@Override
	protected void onIntentionFollow(Creature target)
	{
	}

	@Override
	protected void onIntentionPickUp(WorldObject item)
	{
	}

	@Override
	protected void onIntentionInteract(WorldObject object)
	{
	}

	@Override
	protected void onActionAttacked(Creature attacker)
	{
	}

	@Override
	protected void onActionAggression(Creature target, int aggro)
	{
	}

	@Override
	protected void onActionBlocked(Creature attacker)
	{
	}

	@Override
	protected void onActionRooted(Creature attacker)
	{
	}

	@Override
	protected void onActionForgetObject(WorldObject object)
	{
	}

	@Override
	protected void onActionCancel()
	{
	}

	@Override
	protected void onActionDeath()
	{
	}

	@Override
	protected void onActionFakeDeath()
	{
	}

	@Override
	protected void onActionFinishCasting()
	{
	}

	@Override
	protected void clientActionFailed()
	{
	}

	@Override
	public void moveToPawn(WorldObject pawn, int offset)
	{
	}

	@Override
	protected void clientStoppedMoving()
	{
	}

	@Override
	public AirShip getActor()
	{
		return (AirShip) this._actor;
	}
}
