package org.l2jmobius.gameserver.network.clientpackets.relics;

import org.l2jmobius.gameserver.config.RelicSystemConfig;
import org.l2jmobius.gameserver.data.holders.RelicDataHolder;
import org.l2jmobius.gameserver.data.xml.RelicData;
import org.l2jmobius.gameserver.data.xml.SkillData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.PlayerRelicData;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.relics.ExRelicsActiveInfo;

public class RequestRelicsActive extends ClientPacket
{
	private int _relicId;

	@Override
	protected void readImpl()
	{
		this._relicId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		int relicLevel = 0;
		if (player != null)
		{
			for (PlayerRelicData relic : player.getRelics())
			{
				if (relic.getRelicId() == this._relicId)
				{
					relicLevel = relic.getRelicLevel();
					break;
				}
			}

			int skillId = RelicData.getInstance().getRelicSkillId(this._relicId, 0);
			int skillLevel = relicLevel + 1;
			player.sendPacket(new ExRelicsActiveInfo(this._relicId, relicLevel));
			player.getVariables().set("ACTIVE_RELIC", this._relicId);
			player.getVariables().storeMe();
			Skill relicSkill = SkillData.getInstance().getSkill(skillId, skillLevel);
			if (relicSkill != null)
			{
				for (RelicDataHolder relicx : RelicData.getInstance().getRelics())
				{
					Skill skill = player.getKnownSkill(relicx.getEnchantHolderByEnchant(0).getSkillId());
					if (skill != null)
					{
						player.removeSkill(skill);
						if (RelicSystemConfig.RELIC_SYSTEM_DEBUG_ENABLED)
						{
							player.sendMessage("Relic Skill Id: " + skill.getId() + " Lvl: " + skill.getLevel() + " was removed.");
						}
					}
				}

				player.addSkill(relicSkill, true);
				if (RelicSystemConfig.RELIC_SYSTEM_DEBUG_ENABLED)
				{
					player.sendMessage("Relic Skill Id: " + skillId + " Lvl: " + skillLevel + " was added.");
				}
			}
			else
			{
				player.sendMessage("Relic skill does not exist!");
			}
		}
	}
}
