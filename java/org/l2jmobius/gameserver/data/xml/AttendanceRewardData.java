package org.l2jmobius.gameserver.data.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.l2jmobius.commons.util.IXmlReader;
import org.l2jmobius.gameserver.config.AttendanceRewardsConfig;
import org.l2jmobius.gameserver.data.holders.AttendanceItemHolder;
import org.l2jmobius.gameserver.model.StatSet;
import org.w3c.dom.Document;

public class AttendanceRewardData implements IXmlReader
{
	private static final Logger LOGGER = Logger.getLogger(AttendanceRewardData.class.getName());
	private final List<AttendanceItemHolder> _rewards = new ArrayList<>();
	private int _rewardsCount = 0;

	protected AttendanceRewardData()
	{
		this.load();
	}

	@Override
	public void load()
	{
		if (AttendanceRewardsConfig.ENABLE_ATTENDANCE_REWARDS)
		{
			this._rewards.clear();
			this.parseDatapackFile("data/AttendanceRewards.xml");
			this._rewardsCount = this._rewards.size();
			LOGGER.info(this.getClass().getSimpleName() + ": Loaded " + this._rewardsCount + " rewards.");
		}
		else
		{
			LOGGER.info(this.getClass().getSimpleName() + ": Disabled.");
		}
	}

	@Override
	public void parseDocument(Document document, File file)
	{
		this.forEach(document, "list", listNode -> this.forEach(listNode, "item", rewardNode -> {
			StatSet set = new StatSet(this.parseAttributes(rewardNode));
			int itemId = set.getInt("id");
			int itemCount = set.getInt("count");
			int highlight = set.getInt("highlight", 0);
			if (ItemData.getInstance().getTemplate(itemId) == null)
			{
				LOGGER.info(this.getClass().getSimpleName() + ": Item with id " + itemId + " does not exist.");
			}
			else
			{
				this._rewards.add(new AttendanceItemHolder(itemId, itemCount, highlight));
			}
		}));
	}

	public List<AttendanceItemHolder> getRewards()
	{
		return this._rewards;
	}

	public int getRewardsCount()
	{
		return this._rewardsCount;
	}

	public static AttendanceRewardData getInstance()
	{
		return AttendanceRewardData.SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final AttendanceRewardData INSTANCE = new AttendanceRewardData();
	}
}
