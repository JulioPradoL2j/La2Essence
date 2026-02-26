package org.l2jmobius.gameserver.network.serverpackets.newhenna;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.HennaPatternPotentialData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.henna.Henna;
import org.l2jmobius.gameserver.model.item.henna.HennaPoten;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class NewHennaList extends ServerPacket
{
	private final HennaPoten[] _hennaId;
	private final int _dailyStep;
	private final int _dailyCount;
	private final int _availableSlots;
	private final int _resetCount;
	private final int _sendType;
	private List<ItemHolder> _resetData = new ArrayList<>();

	public NewHennaList(Player player, int sendType)
	{
		this._dailyStep = player.getDyePotentialDailyStep();
		this._dailyCount = player.getDyePotentialDailyCount();
		this._hennaId = player.getHennaPotenList();
		this._availableSlots = player.getAvailableHennaSlots();
		this._resetCount = player.getDyePotentialDailyEnchantReset();
		this._resetData = HennaPatternPotentialData.getInstance().getEnchantReset();
		this._sendType = sendType;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_NEW_HENNA_LIST.writeId(this, buffer);
		buffer.writeByte(this._sendType);
		buffer.writeShort(this._dailyStep);
		buffer.writeShort(this._dailyCount);
		buffer.writeShort(this._resetCount + 1);
		buffer.writeShort(-1);
		buffer.writeInt(this._resetData.size());

		for (ItemHolder resetInfo : this._resetData)
		{
			buffer.writeInt(resetInfo.getId());
			buffer.writeLong(resetInfo.getCount());
		}

		buffer.writeInt(this._hennaId.length);

		for (int i = 1; i <= this._hennaId.length; i++)
		{
			HennaPoten hennaPoten = this._hennaId[i - 1];
			Henna henna = this._hennaId[i - 1].getHenna();
			buffer.writeInt(henna != null ? henna.getDyeId() : 0);
			buffer.writeInt(hennaPoten.getPotenId());
			buffer.writeByte(i != this._availableSlots);
			buffer.writeShort(hennaPoten.getEnchantLevel());
			buffer.writeInt(hennaPoten.getEnchantExp());
			buffer.writeShort(hennaPoten.getActiveStep());
			buffer.writeShort(this._dailyStep);
			buffer.writeShort(this._dailyCount);
			buffer.writeShort(hennaPoten.getUnlockSlot());
		}
	}
}
