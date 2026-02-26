package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExBrPremiumState extends ServerPacket
{
	private final Player _player;

	public ExBrPremiumState(Player player)
	{
		this._player = player;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BR_NOTIFY_PREMIUM_STATE.writeId(this, buffer);
		buffer.writeInt(this._player.getObjectId());
		buffer.writeByte(this._player.hasPremiumStatus() || this._player.getVipTier() > 0);
	}
}
