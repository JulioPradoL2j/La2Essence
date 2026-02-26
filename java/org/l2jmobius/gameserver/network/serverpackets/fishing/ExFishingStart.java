package org.l2jmobius.gameserver.network.serverpackets.fishing;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.interfaces.ILocational;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExFishingStart extends ServerPacket
{
	private final Player _player;
	private final int _fishType;
	private final ILocational _baitLocation;

	public ExFishingStart(Player player, int fishType, ILocational baitLocation)
	{
		this._player = player;
		this._fishType = fishType;
		this._baitLocation = baitLocation;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_FISHING_START.writeId(this, buffer);
		buffer.writeInt(this._player.getObjectId());
		buffer.writeByte(this._fishType);
		buffer.writeInt(this._baitLocation.getX());
		buffer.writeInt(this._baitLocation.getY());
		buffer.writeInt(this._baitLocation.getZ());
		buffer.writeByte(1);
	}
}
