package org.l2jmobius.gameserver.network.serverpackets;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.HennaData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.henna.Henna;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class HennaEquipList extends ServerPacket
{
	private final Player _player;
	private final List<Henna> _hennaEquipList;

	public HennaEquipList(Player player)
	{
		this._player = player;
		this._hennaEquipList = HennaData.getInstance().getHennaList(player);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.HENNA_EQUIP_LIST.writeId(this, buffer);
		buffer.writeLong(this._player.getAdena());
		buffer.writeInt(3);
		buffer.writeInt(this._hennaEquipList.size());

		for (Henna henna : this._hennaEquipList)
		{
			if (this._player.getInventory().getItemByItemId(henna.getDyeItemId()) != null)
			{
				buffer.writeInt(henna.getDyeId());
				buffer.writeInt(henna.getDyeItemId());
				buffer.writeInt(henna.getWearCount());
				buffer.writeLong(henna.getWearFee());
			}
		}
	}
}
