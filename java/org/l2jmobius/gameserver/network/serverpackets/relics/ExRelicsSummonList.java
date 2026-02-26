package org.l2jmobius.gameserver.network.serverpackets.relics;

import java.util.Comparator;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.RelicSystemConfig;
import org.l2jmobius.gameserver.data.holders.RelicSummonCategoryHolder;
import org.l2jmobius.gameserver.data.xml.RelicData;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRelicsSummonList extends ServerPacket
{
	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		if (RelicSystemConfig.RELIC_SYSTEM_ENABLED)
		{
			ServerPackets.EX_RELICS_SUMMON_LIST.writeId(this, buffer);
			buffer.writeInt(RelicData.getInstance().getRelicActiveCategories().size());

			for (RelicSummonCategoryHolder category : RelicData.getInstance().getRelicActiveCategories().stream().sorted(Comparator.comparingInt(RelicSummonCategoryHolder::getCategoryId)).toList())
			{
				buffer.writeInt(category.getCategoryId());
				buffer.writeInt(0);
			}
		}
	}
}
