package org.l2jmobius.gameserver.config.custom;

import org.l2jmobius.commons.util.ConfigReader;

public class CustomDepositableItemsConfig
{
	public static final String CUSTOM_DEPOSITABLE_ITEMS_CONFIG_FILE = "./config/Custom/CustomDepositableItems.ini";
	public static boolean CUSTOM_DEPOSITABLE_ENABLED;
	public static boolean CUSTOM_DEPOSITABLE_QUEST_ITEMS;

	public static void load()
	{
		ConfigReader config = new ConfigReader("./config/Custom/CustomDepositableItems.ini");
		CUSTOM_DEPOSITABLE_ENABLED = config.getBoolean("CustomDepositableEnabled", false);
		CUSTOM_DEPOSITABLE_QUEST_ITEMS = config.getBoolean("DepositableQuestItems", false);
	}
}
