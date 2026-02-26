package org.l2jmobius.gameserver.network.serverpackets.enchant.multi;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.EnchantItemRequest;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExResultSetMultiEnchantItemList extends ServerPacket
{
	private final Player _player;
	private final int _resultType;

	public ExResultSetMultiEnchantItemList(Player player, int resultType)
	{
		this._player = player;
		this._resultType = resultType;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		if (this._player.getRequest(EnchantItemRequest.class) != null)
		{
			ServerPackets.EX_RES_SET_MULTI_ENCHANT_ITEM_LIST.writeId(this, buffer);
			buffer.writeInt(this._resultType);
		}
	}
}
