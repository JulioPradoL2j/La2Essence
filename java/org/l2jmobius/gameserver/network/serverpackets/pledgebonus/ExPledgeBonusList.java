package org.l2jmobius.gameserver.network.serverpackets.pledgebonus;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.ClanRewardData;
import org.l2jmobius.gameserver.model.clan.ClanRewardBonus;
import org.l2jmobius.gameserver.model.clan.enums.ClanRewardType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPledgeBonusList extends ServerPacket
{
	private final List<Integer> _memberBonuses = new LinkedList<>();
	private final List<Integer> _huntingBonuses = new LinkedList<>();

	public ExPledgeBonusList()
	{
		ClanRewardData.getInstance().getClanRewardBonuses(ClanRewardType.MEMBERS_ONLINE).stream().sorted(Comparator.comparingInt(ClanRewardBonus::getLevel)).forEach(bonus -> this._memberBonuses.add(bonus.getSkillReward().getSkillId()));
		ClanRewardData.getInstance().getClanRewardBonuses(ClanRewardType.HUNTING_MONSTERS).stream().sorted(Comparator.comparingInt(ClanRewardBonus::getLevel)).forEach(bonus -> this._huntingBonuses.add(bonus.getSkillReward().getSkillId()));
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_BONUS_LIST.writeId(this, buffer);
		buffer.writeByte(0);

		for (int skillId : this._memberBonuses)
		{
			buffer.writeInt(skillId);
		}

		buffer.writeByte(0);

		for (int skillId : this._huntingBonuses)
		{
			buffer.writeInt(skillId);
		}
	}
}
