package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.enums.Movie;

public class ExStopScenePlayer extends ServerPacket
{
	private final Movie _movie;

	public ExStopScenePlayer(Movie movie)
	{
		this._movie = movie;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_STOP_SCENE_PLAYER.writeId(this, buffer);
		buffer.writeInt(this._movie.getClientId());
	}
}
