package org.l2jmobius.gameserver.network.clientpackets.relics;

import java.util.List;

import org.l2jmobius.gameserver.config.RelicSystemConfig;
import org.l2jmobius.gameserver.data.holders.RelicSummonCategoryHolder;
import org.l2jmobius.gameserver.data.xml.RelicData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.RelicSummonRequest;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.relics.ExRelicsSummonResult;

public class RequestRelicsIdSummon extends ClientPacket
{
	private int _summonCategoryId;

	@Override
	protected void readImpl()
	{
		this._summonCategoryId = this.readInt();
		this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			RelicSummonCategoryHolder category = RelicData.getInstance().getRelicSummonCategory(this._summonCategoryId);
			if (category != null && player.destroyItemByItemId(ItemProcessType.DESTROY, category.getPriceId(), category.getPriceCount(), player, false))
			{
				List<Integer> obtainedRelics = RelicData.getInstance().generateSummonRelics(category.getSummonCount());
				if (obtainedRelics.isEmpty())
				{
					player.sendPacket(SystemMessageId.AN_ERROR_HAS_OCCURRED_PLEASE_TRY_AGAIN_LATER);
					PacketLogger.finer("Summon Category: " + category.getCategoryId() + " generated 0 relics!");
				}
				else
				{
					for (int relicId : obtainedRelics)
					{
						player.handleRelicAcquisition(relicId);
						if (RelicSystemConfig.RELIC_SYSTEM_DEBUG_ENABLED)
						{
							player.sendMessage("Summoned relic ID: " + relicId);
						}
					}

					player.addRequest(new RelicSummonRequest(player));
					player.sendPacket(new ExRelicsSummonResult(category, obtainedRelics));
				}
			}
		}
	}
}
