package org.l2jmobius.gameserver.network.clientpackets.gacha;

import java.util.List;
import java.util.Map.Entry;

import org.l2jmobius.gameserver.managers.events.UniqueGachaManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.holders.GachaItemHolder;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.gacha.UniqueGachaGame;
import org.l2jmobius.gameserver.network.serverpackets.gacha.UniqueGachaInvenAddItem;

public class ExUniqueGachaGame extends ClientPacket
{
	private int _gameCount;

	@Override
	protected void readImpl()
	{
		this._gameCount = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Entry<List<GachaItemHolder>, Boolean> pair = UniqueGachaManager.getInstance().tryToRoll(player, this._gameCount);
			List<GachaItemHolder> rewards = pair.getKey();
			boolean rare = pair.getValue();
			player.sendPacket(new UniqueGachaGame(rewards.isEmpty() ? 0 : 1, player, rewards, rare));
			player.sendPacket(new UniqueGachaInvenAddItem(rewards));
		}
	}
}
