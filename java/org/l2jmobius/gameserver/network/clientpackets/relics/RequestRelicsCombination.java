package org.l2jmobius.gameserver.network.clientpackets.relics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.l2jmobius.gameserver.config.RelicSystemConfig;
import org.l2jmobius.gameserver.data.holders.RelicCompoundFeeHolder;
import org.l2jmobius.gameserver.data.xml.RelicData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.RelicGrade;
import org.l2jmobius.gameserver.model.actor.holders.player.PlayerRelicData;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ExShowScreenMessage;
import org.l2jmobius.gameserver.network.serverpackets.relics.ExRelicsCollectionUpdate;
import org.l2jmobius.gameserver.network.serverpackets.relics.ExRelicsCombination;
import org.l2jmobius.gameserver.network.serverpackets.relics.ExRelicsList;
import org.l2jmobius.gameserver.network.serverpackets.relics.ExRelicsUpdateList;

public class RequestRelicsCombination extends ClientPacket
{
	private int _relicsUsedGrade;
	private int _relicsUsedCount;
	private final List<Integer> _ingredientIds = new LinkedList<>();

	@Override
	protected void readImpl()
	{
		this._relicsUsedGrade = this.readInt();
		this._relicsUsedCount = this.readInt();

		while (this.remaining() > 0)
		{
			this._ingredientIds.add(this.readInt());
		}
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Collection<PlayerRelicData> storedRelics = player.getRelics();
			List<Integer> unconfirmedRelics = new ArrayList<>();
			int compoundAttempts = this._relicsUsedCount / 4;

			for (PlayerRelicData relic : storedRelics)
			{
				if (relic.getRelicIndex() >= 300 && relic.getRelicCount() == 1)
				{
					unconfirmedRelics.add(relic.getRelicId());
				}
			}

			if (unconfirmedRelics.size() == RelicSystemConfig.RELIC_UNCONFIRMED_LIST_LIMIT)
			{
				player.sendPacket(SystemMessageId.SUMMON_COMPOUND_IS_UNAVAILABLE_AS_YOU_HAVE_MORE_THAN_100_UNCONFIRMED_DOLLS);
			}
			else
			{
				RelicCompoundFeeHolder feeHolder = RelicData.getInstance().getCompoundFeeHolderByGrade(RelicGrade.values()[this._relicsUsedGrade]);
				if (!player.destroyItemByItemId(ItemProcessType.FEE, feeHolder.getId(), feeHolder.getCount() * compoundAttempts, player, true))
				{
					player.sendPacket(SystemMessageId.AUTO_COMPOUNDING_IS_CANCELLED_NOT_ENOUGH_MONEY);
				}
				else
				{
					if (RelicSystemConfig.RELIC_SYSTEM_DEBUG_ENABLED)
					{
						player.sendMessage("Compound Ingredients: " + this._relicsUsedCount);
					}

					int ingredientIndex = 0;

					for (int ingredientId : this._ingredientIds)
					{
						ingredientIndex++;
						PlayerRelicData ingredientRelic = storedRelics.stream().filter(relicx -> relicx.getRelicId() == ingredientId && relicx.getRelicIndex() < 300).findFirst().orElse(null);
						if (ingredientRelic != null && ingredientRelic.getRelicCount() > 0)
						{
							ingredientRelic.setRelicCount(ingredientRelic.getRelicCount() - 1);
							if (RelicSystemConfig.RELIC_SYSTEM_DEBUG_ENABLED)
							{
								player.sendMessage(String.format("Ingredient Relic %d data updated, ID: %d, Count: %d", ingredientIndex, ingredientRelic.getRelicId(), ingredientRelic.getRelicCount()));
							}
						}
					}

					ArrayList<Integer> successCompoundIds = new ArrayList<>();
					ArrayList<Integer> failCompoundIds = new ArrayList<>();

					for (int i = 0; i < compoundAttempts; i++)
					{
						Entry<Boolean, Integer> result = RelicData.getInstance().getRelicByCompound(RelicGrade.values()[this._relicsUsedGrade]);
						int obtainedRelicId = result.getValue();
						if (result.getKey())
						{
							successCompoundIds.add(obtainedRelicId);
						}
						else
						{
							failCompoundIds.add(obtainedRelicId);
						}

						PlayerRelicData existingRelic = null;

						for (PlayerRelicData relicx : storedRelics)
						{
							if (relicx.getRelicId() == obtainedRelicId)
							{
								existingRelic = relicx;
								break;
							}
						}

						PlayerRelicData newRelic = new PlayerRelicData(obtainedRelicId, 0, 0, 0, 0L);
						if (existingRelic != null)
						{
							existingRelic.setRelicCount(existingRelic.getRelicCount() + 1);
							player.sendPacket(new ExRelicsUpdateList(1, existingRelic.getRelicId(), 0, existingRelic.getRelicCount() + 1));
							if (RelicSystemConfig.RELIC_SYSTEM_DEBUG_ENABLED)
							{
								player.sendMessage("Existing relic id: " + obtainedRelicId + " count increased.");
							}

							if (existingRelic.getRelicIndex() == 0 && !player.isRelicRegistered(existingRelic.getRelicId(), existingRelic.getRelicLevel()))
							{
								player.sendPacket(new ExRelicsCollectionUpdate(player, existingRelic.getRelicId(), existingRelic.getRelicLevel()));
							}
						}
						else
						{
							newRelic.setRelicIndex(0);
							storedRelics.add(newRelic);
							player.sendPacket(new ExRelicsUpdateList(1, newRelic.getRelicId(), 0, 0));
							if (newRelic.getRelicIndex() == 0 && !player.isRelicRegistered(newRelic.getRelicId(), newRelic.getRelicLevel()))
							{
								player.sendPacket(new ExRelicsCollectionUpdate(player, newRelic.getRelicId(), newRelic.getRelicLevel()));
							}
						}
					}

					int successCount = successCompoundIds.size();
					int failCount = failCompoundIds.size();
					if (successCount > failCount)
					{
						player.sendPacket(new ExShowScreenMessage("Relics compound has failed.", 2, 5000, 0, true, false));
					}

					player.sendMessage("You obtained through compounding: " + compoundAttempts + " relics.");
					player.sendMessage("Relics compound summary: " + successCompoundIds.size() + " succeded and " + failCompoundIds.size() + " failed.");
					player.sendPacket(new ExRelicsCombination(player, successCompoundIds, failCompoundIds));
					player.sendPacket(new ExRelicsList(player));
					player.storeRelics();
				}
			}
		}
	}
}
