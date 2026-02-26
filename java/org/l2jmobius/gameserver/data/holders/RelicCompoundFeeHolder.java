package org.l2jmobius.gameserver.data.holders;

import org.l2jmobius.gameserver.model.actor.enums.player.RelicGrade;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;

public class RelicCompoundFeeHolder extends ItemHolder
{
	private final RelicGrade _grade;

	public RelicCompoundFeeHolder(RelicGrade grade, int id, long count)
	{
		super(id, count);
		this._grade = grade;
	}

	public RelicGrade getFeeGrade()
	{
		return this._grade;
	}
}
