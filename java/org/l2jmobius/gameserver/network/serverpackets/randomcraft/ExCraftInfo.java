package org.l2jmobius.gameserver.network.serverpackets.randomcraft;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.itemcontainer.PlayerRandomCraft;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCraftInfo extends ServerPacket
{
	private final PlayerRandomCraft _randomCraft;

	public ExCraftInfo(Player player)
	{
		this._randomCraft = player.getRandomCraft();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CRAFT_INFO.writeId(this, buffer);
		buffer.writeInt(this._randomCraft.getFullCraftPoints());
		buffer.writeInt(this._randomCraft.getCraftPoints());
		buffer.writeByte(this._randomCraft.isSayhaRoll());
	}
}
