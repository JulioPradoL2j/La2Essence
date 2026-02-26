package org.l2jmobius.gameserver.network.serverpackets.appearance;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCuriousHouseMemberUpdate extends ServerPacket
{
	public int _objId;
	public int _maxHp;
	public int _maxCp;
	public int _currentHp;
	public int _currentCp;

	public ExCuriousHouseMemberUpdate(Player player)
	{
		this._objId = player.getObjectId();
		this._maxHp = (int) player.getMaxHp();
		this._maxCp = player.getMaxCp();
		this._currentHp = (int) player.getCurrentHp();
		this._currentCp = (int) player.getCurrentCp();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CURIOUS_HOUSE_MEMBER_UPDATE.writeId(this, buffer);
		buffer.writeInt(this._objId);
		buffer.writeInt(this._maxHp);
		buffer.writeInt(this._maxCp);
		buffer.writeInt(this._currentHp);
		buffer.writeInt(this._currentCp);
	}
}
