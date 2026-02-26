package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.serverpackets.ExRpItemLink;

public class RequestExRqItemLink extends ClientPacket
{
	private int _objectId;

	@Override
	protected void readImpl()
	{
		this._objectId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			WorldObject object = World.getInstance().findObject(this._objectId);
			if (object != null && object.isItem())
			{
				Item item = (Item) object;
				if (item.isPublished())
				{
					player.sendPacket(new ExRpItemLink(item));
				}
			}
		}
	}
}
