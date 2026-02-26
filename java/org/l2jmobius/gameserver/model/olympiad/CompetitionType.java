package org.l2jmobius.gameserver.model.olympiad;

public enum CompetitionType
{
	CLASSED("classed"),
	NON_CLASSED("non-classed"),
	OTHER("other");

	private final String _name;

	private CompetitionType(String name)
	{
		this._name = name;
	}

	@Override
	public String toString()
	{
		return this._name;
	}
}
