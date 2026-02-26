package org.l2jmobius.gameserver.network.serverpackets;

import java.util.List;
import java.util.stream.Collectors;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.HennaCombinationData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.henna.CombinationHenna;
import org.l2jmobius.gameserver.model.item.henna.Henna;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class NewHennaComposeProbList extends ServerPacket
{
	private final Player _player;

	public NewHennaComposeProbList(Player player)
	{
		this._player = player;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		int allHennaSlots = this._player.getAvailableHennaSlots();
		int emptyHennaSlots = this._player.getHennaEmptySlots();
		ServerPackets.EX_NEW_HENNA_COMPOSE_PROB_LIST.writeId(this, buffer);
		buffer.writeInt(allHennaSlots - emptyHennaSlots);

		for (int slot = 1; slot <= allHennaSlots; slot++)
		{
			Henna henna = this._player.getHenna(slot);
			if (henna != null)
			{
				List<CombinationHenna> hennaList = HennaCombinationData.getInstance().getHenna().stream().filter(h -> h.getHenna() == henna.getDyeId()).collect(Collectors.toList());
				buffer.writeInt(henna.getDyeId());
				buffer.writeInt(hennaList.size());

				for (CombinationHenna item : hennaList)
				{
					buffer.writeInt(item.getItemTwo());
					buffer.writeInt((int) (item.getChance() * 100.0F));
				}
			}
		}
	}
}
