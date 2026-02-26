package org.l2jmobius.gameserver.config;

import org.l2jmobius.commons.util.ConfigReader;

public class RandomCraftConfig
{
	public static final String RANDOM_CRAFT_FILE = "./config/RandomCraft.ini";
	public static boolean ENABLE_RANDOM_CRAFT;
	public static int RANDOM_CRAFT_REFRESH_FEE;
	public static int RANDOM_CRAFT_CREATE_FEE;
	public static boolean DROP_RANDOM_CRAFT_MATERIALS;

	public static void load()
	{
		ConfigReader config = new ConfigReader("./config/RandomCraft.ini");
		ENABLE_RANDOM_CRAFT = config.getBoolean("RandomCraftEnabled", true);
		RANDOM_CRAFT_REFRESH_FEE = config.getInt("RandomCraftRefreshFee", 50000);
		RANDOM_CRAFT_CREATE_FEE = config.getInt("RandomCraftCreateFee", 300000);
		DROP_RANDOM_CRAFT_MATERIALS = config.getBoolean("DropRandomCraftMaterials", true);
	}
}
