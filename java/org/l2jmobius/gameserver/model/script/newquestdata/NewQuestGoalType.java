package org.l2jmobius.gameserver.model.script.newquestdata;

public enum NewQuestGoalType
{
	TALK(0),
	KILL(1),
	COLLECT(2);

	private final int _id;

	private NewQuestGoalType(int id)
	{
		this._id = id;
	}

	public int getId()
	{
		return this._id;
	}
}
