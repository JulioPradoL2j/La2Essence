package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Map;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.BeautyShopData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.appearance.PlayerAppearance;
import org.l2jmobius.gameserver.model.beautyshop.BeautyItem;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExResponseBeautyList extends ServerPacket
{
	public static final int SHOW_FACESHAPE = 1;
	public static final int SHOW_HAIRSTYLE = 0;
	private final Player _player;
	private final int _type;
	private final Map<Integer, BeautyItem> _beautyItem;

	public ExResponseBeautyList(Player player, int type)
	{
		this._player = player;
		this._type = type;
		PlayerAppearance appearance = player.getAppearance();
		if (type == 0)
		{
			this._beautyItem = BeautyShopData.getInstance().getBeautyData(player.getRace(), appearance.getSexType()).getHairList();
		}
		else
		{
			this._beautyItem = BeautyShopData.getInstance().getBeautyData(player.getRace(), appearance.getSexType()).getFaceList();
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESPONSE_BEAUTY_LIST.writeId(this, buffer);
		buffer.writeLong(this._player.getAdena());
		buffer.writeLong(this._player.getBeautyTickets());
		buffer.writeInt(this._type);
		buffer.writeInt(this._beautyItem.size());

		for (BeautyItem item : this._beautyItem.values())
		{
			buffer.writeInt(item.getId());
			buffer.writeInt(1);
		}

		buffer.writeInt(0);
	}
}
