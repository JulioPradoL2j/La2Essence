package org.l2jmobius.gameserver.ai;

import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Boat;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.serverpackets.VehicleDeparture;
import org.l2jmobius.gameserver.network.serverpackets.VehicleInfo;
import org.l2jmobius.gameserver.network.serverpackets.VehicleStarted;

public class BoatAI extends CreatureAI
{
	public BoatAI(Boat boat)
	{
		super(boat);
	}

	@Override
	protected void moveTo(int x, int y, int z)
	{
		if (!this._actor.isMovementDisabled())
		{
			if (!this._actor.isMoving())
			{
				this._actor.broadcastPacket(new VehicleStarted(this.getActor(), 1));
			}

			this._actor.moveToLocation(x, y, z, 0);
			this._actor.broadcastPacket(new VehicleDeparture(this.getActor()));
		}
	}

	@Override
	public void clientStopMoving(Location loc)
	{
		if (this._actor.isMoving())
		{
			this._actor.stopMove(loc);
			this._actor.broadcastPacket(new VehicleStarted(this.getActor(), 0));
			this._actor.broadcastPacket(new VehicleInfo(this.getActor()));
		}
		else
		{
			if (loc != null)
			{
				this._actor.broadcastPacket(new VehicleStarted(this.getActor(), 0));
				this._actor.broadcastPacket(new VehicleInfo(this.getActor()));
			}
		}
	}

	@Override
	public void describeStateToPlayer(Player player)
	{
		if (this._actor.isMoving())
		{
			player.sendPacket(new VehicleDeparture(this.getActor()));
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
	public Boat getActor()
	{
		return (Boat) this._actor;
	}
}
