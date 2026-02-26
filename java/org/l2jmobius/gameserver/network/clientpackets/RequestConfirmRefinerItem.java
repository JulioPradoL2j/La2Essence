package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.data.xml.VariationData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.options.VariationFee;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ExPutIntensiveResultForVariationMake;

public class RequestConfirmRefinerItem extends AbstractRefinePacket
{
	private int _targetItemObjId;
	private int _refinerItemObjId;

	@Override
	protected void readImpl()
	{
		this._targetItemObjId = this.readInt();
		this._refinerItemObjId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Item targetItem = player.getInventory().getItemByObjectId(this._targetItemObjId);
			if (targetItem != null)
			{
				Item refinerItem = player.getInventory().getItemByObjectId(this._refinerItemObjId);
				if (refinerItem != null)
				{
					VariationFee fee = VariationData.getInstance().getFee(targetItem.getId(), refinerItem.getId());
					if (fee != null && isValid(player, targetItem, refinerItem))
					{
						player.sendPacket(new ExPutIntensiveResultForVariationMake(this._refinerItemObjId, refinerItem.getId(), 1));
					}
					else
					{
						player.sendPacket(SystemMessageId.THIS_IS_NOT_A_SUITABLE_ITEM);
					}
				}
			}
		}
	}
}
