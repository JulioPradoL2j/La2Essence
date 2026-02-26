package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Set;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.CursedWeaponsManager;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExCursedWeaponList extends ServerPacket
{
	private final Set<Integer> _ids = CursedWeaponsManager.getInstance().getCursedWeaponsIds();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CURSED_WEAPON_LIST.writeId(this, buffer);
		buffer.writeInt(this._ids.size());
		this._ids.forEach(buffer::writeInt);
	}
}
