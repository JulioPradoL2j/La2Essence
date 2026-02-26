package org.l2jmobius.gameserver.network.serverpackets.newskillenchant;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.holders.EnchantStarHolder;
import org.l2jmobius.gameserver.data.xml.SkillEnchantData;
import org.l2jmobius.gameserver.model.ItemInfo;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.AbstractItemPacket;

public class ExSkillEnchantInfo extends AbstractItemPacket
{
	private final Skill _skill;
	private final Player _player;
	private final EnchantStarHolder _starHolder;

	public ExSkillEnchantInfo(Skill skill, Player player)
	{
		this._skill = skill;
		this._player = player;
		this._starHolder = SkillEnchantData.getInstance().getEnchantStar(SkillEnchantData.getInstance().getSkillEnchant(skill.getId()).getStarLevel());
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SKILL_ENCHANT_INFO.writeId(this, buffer);
		buffer.writeInt(this._skill.getId());
		buffer.writeInt(this._skill.getSubLevel());
		buffer.writeInt(this._player.getSkillEnchantExp(this._starHolder.getLevel()));
		buffer.writeInt(this._starHolder.getExpMax());
		buffer.writeInt(SkillEnchantData.getInstance().getChanceEnchantMap(this._skill) * 100);
		buffer.writeShort(this.calculatePacketSize(new ItemInfo(new Item(57))));
		buffer.writeInt(57);
		buffer.writeLong(1000000L);
	}
}
