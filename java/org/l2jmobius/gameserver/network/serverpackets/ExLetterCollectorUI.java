package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.managers.events.LetterCollectorManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExLetterCollectorUI extends ServerPacket
{
	private final int _minimumLevel;

	public ExLetterCollectorUI(Player player)
	{
		this._minimumLevel = player.getLevel() <= LetterCollectorManager.getInstance().getMaxLevel() ? LetterCollectorManager.getInstance().getMinLevel() : PlayerConfig.PLAYER_MAXIMUM_LEVEL;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_LETTER_COLLECTOR_UI_LAUNCHER.writeId(this, buffer);
		buffer.writeByte(1);
		buffer.writeInt(this._minimumLevel);
	}
}
