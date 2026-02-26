package org.l2jmobius.gameserver.model.interfaces;

import org.l2jmobius.gameserver.model.Location;

public interface IPositionable extends ILocational
{
	void setXYZ(int var1, int var2, int var3);

	void setXYZ(ILocational var1);

	void setHeading(int var1);

	void setLocation(Location var1);
}
