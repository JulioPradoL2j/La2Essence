package org.l2jmobius.gameserver.network.clientpackets;

import java.util.logging.Logger;

import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.data.holders.EnchantStarHolder;
import org.l2jmobius.gameserver.data.xml.SkillData;
import org.l2jmobius.gameserver.data.xml.SkillEnchantData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.skill.enums.SkillEnchantType;
import org.l2jmobius.gameserver.model.skill.holders.SkillEnchantHolder;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ExEnchantSkillResult;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.network.serverpackets.newskillenchant.ExSkillEnchantInfo;

public class RequestExEnchantSkill extends ClientPacket
{
	private static final Logger LOGGER_ENCHANT = Logger.getLogger("enchant.skills");
	private SkillEnchantType _type;
	private int _skillId;
	private int _skillLevel;
	private int _skillSubLevel;

	@Override
	protected void readImpl()
	{
		int type = this.readInt();
		if (type >= 0 && type < SkillEnchantType.values().length)
		{
			this._type = SkillEnchantType.values()[type];
			this._skillId = this.readInt();
			this._skillLevel = this.readShort();
			this._skillSubLevel = this.readShort();
		}
		else
		{
			PacketLogger.warning("Client send incorrect type " + type + " on packet: " + this.getClass().getSimpleName());
		}
	}

	@Override
	protected void runImpl()
	{
		if (this.getClient().getFloodProtectors().canPerformPlayerAction())
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				if (this._skillId <= 0 || this._skillLevel <= 0 || this._skillSubLevel < 0)
				{
					PacketLogger.warning(player + " tried to exploit RequestExEnchantSkill!");
				}
				else if (player.isAllowedToEnchantSkills())
				{
					if (!player.isSellingBuffs())
					{
						if (!player.isInOlympiadMode())
						{
							if (!player.isInStoreMode())
							{
								int skillId = player.getReplacementSkill(this._skillId);
								Skill skill = player.getKnownSkill(skillId);
								if (skill != null)
								{
									if (skill.isEnchantable())
									{
										if (skill.getLevel() == this._skillLevel)
										{
											if (skill.getSubLevel() > 0)
											{
												if (this._type == SkillEnchantType.CHANGE)
												{
													int group1 = this._skillSubLevel % 1000;
													int group2 = skill.getSubLevel() % 1000;
													if (group1 != group2)
													{
														PacketLogger.warning(this.getClass().getSimpleName() + ": Client: " + this.getClient() + " send incorrect sub level group: " + group1 + " expected: " + group2 + " for skill " + this._skillId);
														return;
													}
												}
												else if (skill.getSubLevel() + 1 != this._skillSubLevel)
												{
													PacketLogger.warning(this.getClass().getSimpleName() + ": Client: " + this.getClient() + " send incorrect sub level: " + this._skillSubLevel + " expected: " + (skill.getSubLevel() + 1) + " for skill " + this._skillId);
													return;
												}
											}

											SkillEnchantHolder skillEnchantHolder = SkillEnchantData.getInstance().getSkillEnchant(skill.getId());
											if (skillEnchantHolder == null)
											{
												PacketLogger.warning(this.getClass().getSimpleName() + " request enchant skill does not have star lvl skillId-" + skill.getId());
											}
											else
											{
												EnchantStarHolder starHolder = SkillEnchantData.getInstance().getEnchantStar(skillEnchantHolder.getStarLevel());
												if (starHolder == null)
												{
													PacketLogger.warning(this.getClass().getSimpleName() + " request enchant skill does not have star lvl-" + skill.getId());
												}
												else if (player.getAdena() >= 1000000L)
												{
													player.reduceAdena(ItemProcessType.FEE, 1000000L, null, true);
													int starLevel = starHolder.getLevel();
													if (Rnd.get(100) <= SkillEnchantData.getInstance().getChanceEnchantMap(skill))
													{
														Skill enchantedSkill = SkillData.getInstance().getSkill(skillId, this._skillLevel, this._skillSubLevel);
														if (GeneralConfig.LOG_SKILL_ENCHANTS)
														{
															StringBuilder sb = new StringBuilder();
															LOGGER_ENCHANT.info(sb.append("Success, Character:").append(player.getName()).append(" [").append(player.getObjectId()).append("] Account:").append(player.getAccountName()).append(" IP:").append(player.getIPAddress()).append(", +").append(enchantedSkill.getLevel()).append(" ").append(enchantedSkill.getSubLevel()).append(" - ").append(enchantedSkill.getName()).append(" (").append(enchantedSkill.getId()).append("), ").toString());
														}

														long reuse = player.getSkillRemainingReuseTime(skill.getReuseHashCode());
														if (reuse > 0L)
														{
															player.addTimeStamp(enchantedSkill, reuse);
														}

														player.addSkill(enchantedSkill, true);
														SystemMessage sm = new SystemMessage(SystemMessageId.SKILL_ENCHANT_WAS_SUCCESSFUL_S1_HAS_BEEN_ENCHANTED);
														sm.addSkillName(skillId);
														player.sendPacket(sm);
														player.sendPacket(ExEnchantSkillResult.STATIC_PACKET_TRUE);
														player.setSkillEnchantExp(starLevel, 0);
														player.setSkillTryEnchant(starLevel);
														skill = player.getKnownSkill(skillId);
													}
													else
													{
														player.sendPacket(ExEnchantSkillResult.STATIC_PACKET_FALSE);
														int curExp = player.getSkillEnchantExp(starHolder.getLevel());
														if (curExp > 900000)
														{
															player.setSkillEnchantExp(starLevel, 0);
														}
														else
														{
															player.setSkillEnchantExp(starLevel, Math.min(900001, 90000 * player.getSkillTryEnchant(starLevel)));
															player.increaseTrySkillEnchant(starLevel);
														}
													}

													player.sendPacket(new ExSkillEnchantInfo(skill, player));
													player.updateShortcuts(skill.getId(), skill.getLevel(), skill.getSubLevel());
													player.broadcastUserInfo();
													player.sendSkillList();
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
		}
	}
}
