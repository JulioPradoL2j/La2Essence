package org.l2jmobius.gameserver.network.serverpackets.enchant.multi;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.EnchantItemRequest;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExResetSelectMultiEnchantScroll extends ServerPacket
{
	private final Player _player;
	private final int _scrollObjectId;
	private final int _resultType;

	public ExResetSelectMultiEnchantScroll(Player player, int scrollObjectId, int resultType)
	{
		this._player = player;
		this._scrollObjectId = scrollObjectId;
		this._resultType = resultType;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		EnchantItemRequest request = this._player.getRequest(EnchantItemRequest.class);
		if (request != null)
		{
			if (request.getEnchantingScroll() == null)
			{
				request.setEnchantingScroll(this._scrollObjectId);
			}

			ServerPackets.EX_RES_SELECT_MULTI_ENCHANT_SCROLL.writeId(this, buffer);
			buffer.writeInt(this._scrollObjectId);
			buffer.writeInt(this._resultType);
		}
	}
}
