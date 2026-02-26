package org.l2jmobius.gameserver.model.clan.enums;

public enum ClanHallType
{
	AUCTIONABLE(0),
	SIEGEABLE(1),
	OTHER(2);

	private final int _clientVal;

	private ClanHallType(int clientVal)
	{
		this._clientVal = clientVal;
	}

	public int getClientVal()
	{
		return this._clientVal;
	}
}
