package org.l2jmobius.gameserver.network.serverpackets.primeshop;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.primeshop.PrimeShopGroup;
import org.l2jmobius.gameserver.model.primeshop.PrimeShopItem;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExBRProductList extends ServerPacket
{
	private final Player _player;
	private final int _type;
	private final Collection<PrimeShopGroup> _primeList;

	public ExBRProductList(Player player, int type, Collection<PrimeShopGroup> items)
	{
		this._player = player;
		this._type = type;
		this._primeList = items;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BR_PRODUCT_LIST_ACK.writeId(this, buffer);
		buffer.writeLong(this._player.getAdena());
		buffer.writeLong(0L);
		buffer.writeByte(this._type);
		buffer.writeInt(this._primeList.size());

		for (PrimeShopGroup brItem : this._primeList)
		{
			buffer.writeInt(brItem.getBrId());
			buffer.writeByte(brItem.getCat());
			buffer.writeByte(brItem.getPaymentType());
			buffer.writeInt(brItem.getPrice());
			buffer.writeByte(brItem.getPanelType());
			buffer.writeInt(brItem.getRecommended());
			buffer.writeInt(brItem.getStartSale());
			buffer.writeInt(brItem.getEndSale());
			buffer.writeByte(brItem.getDaysOfWeek());
			buffer.writeByte(brItem.getStartHour());
			buffer.writeByte(brItem.getStartMinute());
			buffer.writeByte(brItem.getStopHour());
			buffer.writeByte(brItem.getStopMinute());
			if (brItem.getAccountDailyLimit() > 0 && this._player.getAccountVariables().getInt("PSPDailyCount" + brItem.getBrId(), 0) >= brItem.getAccountDailyLimit())
			{
				buffer.writeInt(brItem.getAccountDailyLimit());
				buffer.writeInt(brItem.getAccountDailyLimit());
			}
			else if (brItem.getAccountBuyLimit() > 0 && this._player.getAccountVariables().getInt("PSPCount" + brItem.getBrId(), 0) >= brItem.getAccountBuyLimit())
			{
				buffer.writeInt(brItem.getAccountBuyLimit());
				buffer.writeInt(brItem.getAccountBuyLimit());
			}
			else
			{
				buffer.writeInt(brItem.getStock());
				buffer.writeInt(brItem.getTotal());
			}

			buffer.writeByte(brItem.getSalePercent());
			buffer.writeByte(brItem.getMinLevel());
			buffer.writeByte(brItem.getMaxLevel());
			buffer.writeInt(brItem.getMinBirthday());
			buffer.writeInt(brItem.getMaxBirthday());
			if (brItem.getAccountDailyLimit() > 0)
			{
				buffer.writeInt(1);
				buffer.writeInt(brItem.getAccountDailyLimit());
			}
			else if (brItem.getAccountBuyLimit() > 0)
			{
				buffer.writeInt(-1);
				buffer.writeInt(brItem.getAccountBuyLimit());
			}
			else
			{
				buffer.writeInt(0);
				buffer.writeInt(0);
			}

			buffer.writeByte(brItem.getItems().size());

			for (PrimeShopItem item : brItem.getItems())
			{
				buffer.writeInt(item.getId());
				buffer.writeInt((int) item.getCount());
				buffer.writeInt(item.getWeight());
				buffer.writeInt(item.isTradable());
			}
		}
	}
}
