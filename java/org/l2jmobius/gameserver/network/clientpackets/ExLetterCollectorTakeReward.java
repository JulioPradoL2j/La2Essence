package org.l2jmobius.gameserver.network.clientpackets;

import java.util.List;

import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.managers.events.LetterCollectorManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.holders.ItemChanceHolder;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.model.itemcontainer.PlayerInventory;
import org.l2jmobius.gameserver.network.SystemMessageId;

public class ExLetterCollectorTakeReward extends ClientPacket
{
	private int _wordId;

	@Override
	protected void readImpl()
	{
		this._wordId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			PlayerInventory inventory = player.getInventory();
			if (inventory != null)
			{
				LetterCollectorManager.LetterCollectorRewardHolder lcrh = LetterCollectorManager.getInstance().getRewards(this._wordId);
				if (lcrh != null)
				{
					for (ItemHolder needLetter : LetterCollectorManager.getInstance().getWord(this._wordId))
					{
						if (inventory.getInventoryItemCount(needLetter.getId(), -1) < needLetter.getCount())
						{
							return;
						}
					}

					for (ItemHolder destroyLetter : LetterCollectorManager.getInstance().getWord(this._wordId))
					{
						if (!player.destroyItemByItemId(ItemProcessType.FEE, destroyLetter.getId(), destroyLetter.getCount(), player, true))
						{
							return;
						}
					}

					ItemChanceHolder rewardItem = this.getRandomReward(lcrh.getRewards(), lcrh.getChance());
					if (rewardItem == null)
					{
						player.sendPacket(SystemMessageId.NOTHING_HAPPENED);
					}
					else
					{
						player.addItem(ItemProcessType.REWARD, rewardItem.getId(), rewardItem.getCount(), rewardItem.getEnchantmentLevel(), player, true);
					}
				}
			}
		}
	}

	protected ItemChanceHolder getRandomReward(List<ItemChanceHolder> rewards, double holderChance)
	{
		double chance = Rnd.get(holderChance);
		double itemChance = 0.0;

		for (ItemChanceHolder rewardItem : rewards)
		{
			itemChance += rewardItem.getChance();
			if (chance <= itemChance)
			{
				if (rewardItem.getId() == -1)
				{
					return null;
				}

				return rewardItem;
			}
		}

		return null;
	}
}
