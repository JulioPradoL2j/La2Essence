package org.l2jmobius.gameserver.network.serverpackets.vip;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ReceiveVipLuckyGameInfo extends ServerPacket
{
	private final short _adenaAmount;
	private final short _lcoinCount;

	public ReceiveVipLuckyGameInfo(Player player)
	{
		this._adenaAmount = (short) player.getAdena();
		Item item = player.getInventory().getItemByItemId(91663);
		this._lcoinCount = item == null ? 0 : (short) item.getCount();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_LUCKY_GAME_INFO.writeId(this, buffer);
		buffer.writeByte(true);
		buffer.writeShort(this._adenaAmount);
		buffer.writeShort(this._lcoinCount);
	}
}
