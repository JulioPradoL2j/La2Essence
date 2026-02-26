package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.serverpackets.VariationProbList;

public class RequestVariationProbList extends ClientPacket
{
	private int _refineryId;
	private int _targetItemId;

	@Override
	protected void readImpl()
	{
		this._refineryId = this.readInt();
		this._targetItemId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Item targetItem = player.getInventory().getItemByItemId(this._targetItemId);
			if (targetItem != null)
			{
				player.sendPacket(new VariationProbList(this._refineryId, targetItem));
			}
		}
	}
}
