package org.l2jmobius.gameserver.model.actor.holders.player;

public class AttendanceInfoHolder
{
	private final int _rewardIndex;
	private final boolean _rewardAvailable;

	public AttendanceInfoHolder(int rewardIndex, boolean rewardAvailable)
	{
		this._rewardIndex = rewardIndex;
		this._rewardAvailable = rewardAvailable;
	}

	public int getRewardIndex()
	{
		return this._rewardIndex;
	}

	public boolean isRewardAvailable()
	{
		return this._rewardAvailable;
	}
}
