package org.l2jmobius.gameserver.network.serverpackets.enchant.single;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExChangedEnchantTargetItemProbList extends ServerPacket
{
	private final List<ExChangedEnchantTargetItemProbList.EnchantProbInfo> _probList;

	public ExChangedEnchantTargetItemProbList(List<ExChangedEnchantTargetItemProbList.EnchantProbInfo> probList)
	{
		this._probList = probList;
	}

	public ExChangedEnchantTargetItemProbList(ExChangedEnchantTargetItemProbList.EnchantProbInfo probInfo)
	{
		this._probList = new ArrayList<>();
		this._probList.add(probInfo);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHANGED_ENCHANT_TARGET_ITEM_PROB_LIST.writeId(this, buffer);
		buffer.writeInt(this._probList.size());

		for (ExChangedEnchantTargetItemProbList.EnchantProbInfo info : this._probList)
		{
			buffer.writeInt(info.itemObjId);
			buffer.writeInt(info.totalSuccessProb);
			buffer.writeInt(info.baseProb);
			buffer.writeInt(info.supportProb);
			buffer.writeInt(info.itemSkillProb);
		}
	}

	public static class EnchantProbInfo
	{
		int itemObjId;
		int totalSuccessProb;
		int baseProb;
		int supportProb;
		int itemSkillProb;

		public EnchantProbInfo(int itemObjId, int totalSuccessProb, int baseProb, int supportProb, int itemSkillProb)
		{
			this.itemObjId = itemObjId;
			this.totalSuccessProb = Math.min(10000, totalSuccessProb);
			this.baseProb = baseProb;
			this.supportProb = supportProb;
			this.itemSkillProb = itemSkillProb;
		}
	}
}
