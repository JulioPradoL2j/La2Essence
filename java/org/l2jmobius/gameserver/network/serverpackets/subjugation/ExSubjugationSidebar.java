package org.l2jmobius.gameserver.network.serverpackets.subjugation;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.PlayerPurgeHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExSubjugationSidebar extends ServerPacket
{
	private final Player _player;
	private final PlayerPurgeHolder _purgeData;

	public ExSubjugationSidebar(Player player, PlayerPurgeHolder purgeData)
	{
		this._player = player;
		this._purgeData = purgeData;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SUBJUGATION_SIDEBAR.writeId(this, buffer);
		buffer.writeInt(this._player == null ? 0 : this._player.getPurgeLastCategory());
		buffer.writeInt(this._purgeData == null ? 0 : this._purgeData.getPoints());
		buffer.writeInt(this._purgeData == null ? 0 : this._purgeData.getKeys());
		buffer.writeInt(0);
	}
}
