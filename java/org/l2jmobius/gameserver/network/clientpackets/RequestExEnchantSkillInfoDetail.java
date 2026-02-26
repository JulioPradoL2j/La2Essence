package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.skill.enums.SkillEnchantType;
import org.l2jmobius.gameserver.network.serverpackets.ExEnchantSkillInfoDetail;

public class RequestExEnchantSkillInfoDetail extends ClientPacket
{
	private SkillEnchantType _type;
	private int _skillId;
	private int _skillLevel;
	private int _skillSubLevel;

	@Override
	protected void readImpl()
	{
		this._type = SkillEnchantType.values()[this.readInt()];
		this._skillId = this.readInt();
		this._skillLevel = this.readShort();
		this._skillSubLevel = this.readShort();
	}

	@Override
	protected void runImpl()
	{
		if (this._skillId > 0 && this._skillLevel > 0 && this._skillSubLevel >= 0)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				player.sendPacket(new ExEnchantSkillInfoDetail(this._type, this._skillId, this._skillLevel, this._skillSubLevel, player));
			}
		}
	}
}
