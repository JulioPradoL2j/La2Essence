package org.l2jmobius.gameserver.config.custom;

import org.l2jmobius.commons.util.ConfigReader;

public class SayuneForAllConfig
{
	public static final String SAYUNE_FOR_ALL_CONFIG_FILE = "./config/Custom/SayuneForAll.ini";
	public static boolean FREE_JUMPS_FOR_ALL;

	public static void load()
	{
		ConfigReader config = new ConfigReader("./config/Custom/SayuneForAll.ini");
		FREE_JUMPS_FOR_ALL = config.getBoolean("FreeJumpsForAll", false);
	}
}
