package org.l2jmobius.gameserver.network.clientpackets.variation;

import org.l2jmobius.gameserver.model.VariationInstance;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.VariationRequest;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.variation.ApplyVariationOption;

public class ExApplyVariationOption extends ClientPacket
{
	private int _enchantedObjectId;
	private int _option1;
	private int _option2;
	private int _option3;

	@Override
	protected void readImpl()
	{
		this._enchantedObjectId = this.readInt();
		this._option1 = this.readInt();
		this._option2 = this.readInt();
		this._option3 = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			VariationRequest request = player.getRequest(VariationRequest.class);
			if (request != null)
			{
				Item targetItem = request.getAugmentedItem();
				VariationInstance augment = request.getAugment();
				int option1Id = augment.getOption1Id();
				int option2Id = augment.getOption2Id();
				int option3Id = augment.getOption3Id();
				if (targetItem.getObjectId() == this._enchantedObjectId && this._option1 == option1Id && this._option2 == option2Id && this._option3 == option3Id)
				{
					targetItem.setAugmentation(augment, true);
					player.sendPacket(new ApplyVariationOption(1, this._enchantedObjectId, this._option1, this._option2, this._option3));
					if (targetItem.isEquipped())
					{
						targetItem.getAugmentation().applyBonus(player);
					}

					player.getStat().recalculateStats(true);
					player.sendItemList();
					player.removeRequest(VariationRequest.class);
				}
				else
				{
					player.sendPacket(new ApplyVariationOption(0, 0, 0, 0, 0));
				}
			}
		}
	}
}
