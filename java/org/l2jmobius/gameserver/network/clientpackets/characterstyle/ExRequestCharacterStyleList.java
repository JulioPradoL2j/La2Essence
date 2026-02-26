package org.l2jmobius.gameserver.network.clientpackets.characterstyle;

import java.util.HashMap;
import java.util.Map;

import org.l2jmobius.gameserver.data.enums.CharacterStyleCategoryType;
import org.l2jmobius.gameserver.data.xml.CharacterStylesData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.characterstyle.ExCharacterStyleList;

public class ExRequestCharacterStyleList extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			for (CharacterStyleCategoryType type : CharacterStyleCategoryType.values())
			{
				Map<Integer, Integer> activeMap = new HashMap<>();
				if (type == CharacterStyleCategoryType.APPEARANCE_WEAPON)
				{
					activeMap.put(0, player.getActiveCharacterStyleId(type, 0));
					activeMap.put(1, player.getActiveCharacterStyleId(type, 1));
				}
				else
				{
					activeMap.put(0, player.getActiveCharacterStyleId(type));
				}

				ItemHolder swapCosts = CharacterStylesData.getInstance().getSwapCostItemByCategory(type);
				player.sendPacket(new ExCharacterStyleList(type, swapCosts, player.getVariables().getIntegerList("AVAILABLE_CHARACTER_STYLES_" + type), player.getVariables().getIntegerList("FAVORITE_CHARACTER_STYLES_" + type), activeMap));
			}
		}
	}
}
