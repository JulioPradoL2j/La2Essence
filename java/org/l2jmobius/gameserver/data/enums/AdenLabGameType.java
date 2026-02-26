package org.l2jmobius.gameserver.data.enums;

public enum AdenLabGameType
{
	NORMAL,
	SPECIAL,
	INCREDIBLE;

	public static boolean isValidGameType(String value)
	{
		for (AdenLabGameType type : values())
		{
			if (type.name().equals(value.toUpperCase()))
			{
				return true;
			}
		}

		return false;
	}
}
