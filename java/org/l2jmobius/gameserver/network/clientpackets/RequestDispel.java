package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.data.xml.SkillData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.Summon;
import org.l2jmobius.gameserver.model.skill.AbnormalType;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.skill.enums.SkillFinishType;

public class RequestDispel extends ClientPacket
{
	private int _objectId;
	private int _skillId;
	private int _skillLevel;
	private int _skillSubLevel;

	@Override
	protected void readImpl()
	{
		this._objectId = this.readInt();
		this._skillId = this.readInt();
		this._skillLevel = this.readShort();
		this._skillSubLevel = this.readShort();
	}

	@Override
	protected void runImpl()
	{
		if (this._skillId > 0 && this._skillLevel > 0)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				Skill skill = SkillData.getInstance().getSkill(this._skillId, this._skillLevel, this._skillSubLevel);
				if (skill != null)
				{
					if (skill.canBeDispelled() && !skill.isDebuff())
					{
						if (skill.getAbnormalType() != AbnormalType.TRANSFORM)
						{
							if (!skill.isDance() || PlayerConfig.DANCE_CANCEL_BUFF)
							{
								if (player.getObjectId() == this._objectId)
								{
									player.stopSkillEffects(SkillFinishType.REMOVED, this._skillId);
								}
								else
								{
									Summon pet = player.getPet();
									if (pet != null && pet.getObjectId() == this._objectId)
									{
										pet.stopSkillEffects(SkillFinishType.REMOVED, this._skillId);
									}

									Summon servitor = player.getServitor(this._objectId);
									if (servitor != null)
									{
										servitor.stopSkillEffects(SkillFinishType.REMOVED, this._skillId);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
