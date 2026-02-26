package org.l2jmobius.gameserver.config;

import org.l2jmobius.commons.util.ConfigReader;

public class GameAssistantConfig
{
	public static final String GAME_ASSISTANT_CONFIG_FILE = "./config/GameAssistant.ini";
	public static boolean GAME_ASSISTANT_ENABLED;

	public static void load()
	{
		ConfigReader config = new ConfigReader("./config/GameAssistant.ini");
		GAME_ASSISTANT_ENABLED = config.getBoolean("GameAssistantEnabled", false);
	}
}
