package org.l2jmobius.gameserver.network.clientpackets.relics;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.l2jmobius.gameserver.data.holders.RelicCouponHolder;
import org.l2jmobius.gameserver.data.holders.RelicDataHolder;
import org.l2jmobius.gameserver.data.xml.RelicCouponData;
import org.l2jmobius.gameserver.data.xml.RelicData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.player.RelicGrade;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.relics.RelicsProbList;

public class RequestRelicsProbList extends ClientPacket
{
 
	private int _type;
	private int _value;

	@Override
	protected void readImpl()
	{
		this._type = this.readInt();
		this._value = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && (this._type == 4 || this._value < 7))
		{
			Map<Integer, Long> relics = new LinkedHashMap<>();
			switch (this._type)
			{
				case 0:
					for (RelicDataHolder relicHolder : RelicData.getInstance().getRelics().stream().filter(relic -> relic.getSummonChance() > 0L).sorted(Comparator.comparingInt(RelicDataHolder::getGradeOrdinal).reversed()).collect(Collectors.toList()))
					{
						relics.put(relicHolder.getRelicId(), relicHolder.getSummonChance());
					}
					break;
				case 1:
					for (RelicDataHolder relicHolder : RelicData.getInstance().getRelicsByGrade(RelicGrade.values()[this._value]))
					{
						relics.put(relicHolder.getRelicId(), RelicData.getInstance().calculateCompoundChance(relicHolder.getRelicId(), RelicGrade.values()[this._value]));
					}

					if (this._value + 1 != RelicGrade.values().length)
					{
						for (RelicDataHolder relicHolder : RelicData.getInstance().getRelicsByGrade(RelicGrade.values()[this._value + 1]))
						{
							relics.put(relicHolder.getRelicId(), RelicData.getInstance().calculateCompoundChance(relicHolder.getRelicId(), RelicGrade.values()[this._value]));
						}
					}
				case 2:
				case 3:
				default:
					break;
				case 4:
					RelicCouponHolder coupon = RelicCouponData.getInstance().getCouponFromCouponItemId(this._value);
					if (coupon == null)
					{
						return;
					}

					Map<Integer, Long> possibleEntries = RelicCouponData.getInstance().getCachedChances(coupon.getItemId());

					for (Entry<Integer, Long> entry : possibleEntries.entrySet())
					{
						relics.put(entry.getKey(), entry.getValue());
					}
			}

			if (!relics.isEmpty())
			{
				player.sendPacket(new RelicsProbList(this._type, this._value, relics));
			}
		}
	}
}
