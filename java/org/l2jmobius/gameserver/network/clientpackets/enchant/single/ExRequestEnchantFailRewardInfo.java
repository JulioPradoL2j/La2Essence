package org.l2jmobius.gameserver.network.clientpackets.enchant.single;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.enchant.ResetEnchantItemFailRewardInfo;

public class ExRequestEnchantFailRewardInfo extends ClientPacket
{
	private int _itemobjectid;

	@Override
	protected void readImpl()
	{
		this._itemobjectid = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Item addedItem = player.getInventory().getItemByObjectId(this._itemobjectid);
			if (addedItem != null)
			{
				player.sendPacket(new ResetEnchantItemFailRewardInfo(player));
			}
		}
	}
}
