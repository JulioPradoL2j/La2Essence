package org.l2jmobius.gameserver.network.clientpackets.blessing;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.blessing.ExBlessOptionPutItem;

public class RequestBlessOptionPutItem extends ClientPacket
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
			Item item = player.getInventory().getItemByObjectId(this._objectId);
			if (item != null)
			{
				if (player.isProcessingTransaction() || player.isInStoreMode())
				{
					this.getClient().sendPacket(SystemMessageId.YOU_CANNOT_ENCHANT_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
				}
				else if (item.isBlessed())
				{
					this.getClient().sendPacket(SystemMessageId.AUGMENTATION_REQUIREMENTS_ARE_NOT_FULFILLED);
					player.sendPacket(new ExBlessOptionPutItem(0));
				}
				else
				{
					player.sendPacket(new ExBlessOptionPutItem(1));
				}
			}
		}
	}
}
