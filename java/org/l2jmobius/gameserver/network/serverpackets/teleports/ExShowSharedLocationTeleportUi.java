package org.l2jmobius.gameserver.network.serverpackets.teleports;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.data.holders.SharedTeleportHolder;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExShowSharedLocationTeleportUi extends ServerPacket
{
	private final SharedTeleportHolder _teleport;

	public ExShowSharedLocationTeleportUi(SharedTeleportHolder teleport)
	{
		this._teleport = teleport;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHARED_POSITION_TELEPORT_UI.writeId(this, buffer);
		buffer.writeSizedString(this._teleport.getName());
		buffer.writeInt(this._teleport.getId());
		buffer.writeInt(this._teleport.getCount());
		buffer.writeShort(150);
		Location location = this._teleport.getLocation();
		buffer.writeInt(location.getX());
		buffer.writeInt(location.getY());
		buffer.writeInt(location.getZ());
		buffer.writeLong(GeneralConfig.TELEPORT_SHARE_LOCATION_COST);
	}
}
