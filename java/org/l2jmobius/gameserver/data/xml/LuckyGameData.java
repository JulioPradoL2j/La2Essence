package org.l2jmobius.gameserver.data.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.l2jmobius.commons.util.IXmlReader;
import org.l2jmobius.gameserver.data.holders.LuckyGameDataHolder;
import org.l2jmobius.gameserver.model.StatSet;
import org.l2jmobius.gameserver.model.item.holders.ItemChanceHolder;
import org.l2jmobius.gameserver.model.item.holders.ItemPointHolder;
import org.w3c.dom.Document;

public class LuckyGameData implements IXmlReader
{
	private final Map<Integer, LuckyGameDataHolder> _luckyGame = new HashMap<>();
	private final AtomicInteger _serverPlay = new AtomicInteger();

	protected LuckyGameData()
	{
		this.load();
	}

	@Override
	public void load()
	{
		this._luckyGame.clear();
		this.parseDatapackFile("data/LuckyGameData.xml");
		LOGGER.info(this.getClass().getSimpleName() + ": Loaded " + this._luckyGame.size() + " lucky game data.");
	}

	@Override
	public void parseDocument(Document document, File file)
	{
		this.forEach(document, "list", listNode -> this.forEach(listNode, "luckygame", rewardNode -> {
			LuckyGameDataHolder holder = new LuckyGameDataHolder(new StatSet(this.parseAttributes(rewardNode)));
			this.forEach(rewardNode, "common_reward", commonRewardNode -> this.forEach(commonRewardNode, "item", itemNode -> {
				StatSet stats = new StatSet(this.parseAttributes(itemNode));
				holder.addCommonReward(new ItemChanceHolder(stats.getInt("id"), stats.getDouble("chance"), stats.getLong("count")));
			}));
			this.forEach(rewardNode, "unique_reward", uniqueRewardNode -> this.forEach(uniqueRewardNode, "item", itemNode -> holder.addUniqueReward(new ItemPointHolder(new StatSet(this.parseAttributes(itemNode))))));
			this.forEach(rewardNode, "modify_reward", uniqueRewardNode -> {
				holder.setMinModifyRewardGame(this.parseInteger(uniqueRewardNode.getAttributes(), "min_game"));
				holder.setMaxModifyRewardGame(this.parseInteger(uniqueRewardNode.getAttributes(), "max_game"));
				this.forEach(uniqueRewardNode, "item", itemNode -> {
					StatSet stats = new StatSet(this.parseAttributes(itemNode));
					holder.addModifyReward(new ItemChanceHolder(stats.getInt("id"), stats.getDouble("chance"), stats.getLong("count")));
				});
			});
			this._luckyGame.put(this.parseInteger(rewardNode.getAttributes(), "index"), holder);
		}));
	}

	public int getLuckyGameCount()
	{
		return this._luckyGame.size();
	}

	public LuckyGameDataHolder getLuckyGameDataByIndex(int index)
	{
		return this._luckyGame.get(index);
	}

	public int increaseGame()
	{
		return this._serverPlay.incrementAndGet();
	}

	public int getServerPlay()
	{
		return this._serverPlay.get();
	}

	public static LuckyGameData getInstance()
	{
		return LuckyGameData.SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final LuckyGameData INSTANCE = new LuckyGameData();
	}
}
