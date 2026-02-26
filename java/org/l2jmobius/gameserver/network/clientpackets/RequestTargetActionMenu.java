package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;

public class RequestTargetActionMenu extends ClientPacket
{
	private int _objectId;

	@Override
	protected void readImpl()
	{
		this._objectId = this.readInt();
		this.readShort();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (!this.getClient().getFloodProtectors().canPerformPlayerAction())
			{
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else if (player.isTargetingDisabled())
			{
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else
			{
				for (WorldObject object : World.getInstance().getVisibleObjects(player, WorldObject.class))
				{
					if (this._objectId == object.getObjectId())
					{
						if (object.isTargetable() && object.isAutoAttackable(player))
						{
							player.setTarget(object);
						}

						return;
					}
				}

				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
	}
}
