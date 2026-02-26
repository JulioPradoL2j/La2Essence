package org.l2jmobius.gameserver.network.clientpackets.shuttle;

import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.type.WeaponType;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import org.l2jmobius.gameserver.network.serverpackets.shuttle.ExMoveToLocationInShuttle;
import org.l2jmobius.gameserver.network.serverpackets.shuttle.ExStopMoveInShuttle;

public class MoveToLocationInShuttle extends ClientPacket
{
	private int _boatId;
	private int _targetX;
	private int _targetY;
	private int _targetZ;
	private int _originX;
	private int _originY;
	private int _originZ;

	@Override
	protected void readImpl()
	{
		this._boatId = this.readInt();
		this._targetX = this.readInt();
		this._targetY = this.readInt();
		this._targetZ = this.readInt();
		this._originX = this.readInt();
		this._originY = this.readInt();
		this._originZ = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._targetX == this._originX && this._targetY == this._originY && this._targetZ == this._originZ)
			{
				player.sendPacket(new ExStopMoveInShuttle(player, this._boatId));
			}
			else if (player.isAttackingNow() && player.getActiveWeaponItem() != null && player.getActiveWeaponItem().getItemType() == WeaponType.BOW)
			{
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else if (!player.isSitting() && !player.isMovementDisabled())
			{
				player.setInVehiclePosition(new Location(this._targetX, this._targetY, this._targetZ));
				player.broadcastPacket(new ExMoveToLocationInShuttle(player, this._originX, this._originY, this._originZ));
			}
			else
			{
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
	}
}
