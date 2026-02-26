package org.l2jmobius.gameserver.managers;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.config.MagicLampConfig;
import org.l2jmobius.gameserver.data.enums.LampType;
import org.l2jmobius.gameserver.data.holders.MagicLampDataHolder;
import org.l2jmobius.gameserver.data.holders.MagicLampHolder;
import org.l2jmobius.gameserver.data.xml.MagicLampData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.skill.CommonSkill;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.stats.Stat;
import org.l2jmobius.gameserver.network.serverpackets.magiclamp.ExMagicLampInfo;
import org.l2jmobius.gameserver.network.serverpackets.magiclamp.ExMagicLampResult;

public class MagicLampManager
{
	private static final List<MagicLampDataHolder> REWARDS = MagicLampData.getInstance().getLamps();
	public static final int REWARD_COUNT = 1;

	public void useMagicLamp(Player player)
	{
		if (!REWARDS.isEmpty())
		{
			Map<LampType, MagicLampHolder> rewards = new EnumMap<>(LampType.class);
			int count = 0;

			while (count == 0)
			{
				for (MagicLampDataHolder lamp : REWARDS)
				{
					if (lamp.getFromLevel() <= player.getLevel() && player.getLevel() <= lamp.getToLevel() && Rnd.get(100.0) < lamp.getChance())
					{
						rewards.computeIfAbsent(lamp.getType(), _ -> new MagicLampHolder(lamp)).inc();
						if (++count >= 1)
						{
							break;
						}
					}
				}
			}

			rewards.values().forEach(lampx -> {
				int exp = (int) lampx.getExp();
				int sp = (int) lampx.getSp();
				player.addExpAndSp(exp, sp);
				LampType lampType = lampx.getType();
				player.sendPacket(new ExMagicLampResult(exp, lampType.getGrade()));
				player.sendPacket(new ExMagicLampInfo(player));
				manageSkill(player, lampType);
			});
		}
	}

	public void addLampExp(Player player, double exp, int mobLevel, boolean rateModifiers)
	{
		if (MagicLampConfig.ENABLE_MAGIC_LAMP)
		{
			int playerLevel = player.getLevel();
			int levelDifference = mobLevel - playerLevel;
			double baseContributionPercentage;
			if (playerLevel < 20)
			{
				baseContributionPercentage = 0.4;
			}
			else if (playerLevel < 40)
			{
				baseContributionPercentage = 0.3;
			}
			else if (playerLevel < 60)
			{
				baseContributionPercentage = 0.2;
			}
			else if (playerLevel <= 70)
			{
				baseContributionPercentage = 0.15;
			}
			else if (playerLevel <= 85)
			{
				baseContributionPercentage = 0.1;
			}
			else
			{
				baseContributionPercentage = 0.08;
			}

			double levelMultiplier = 1.0;
			if (levelDifference > 0)
			{
				levelMultiplier += levelDifference * 0.1;
			}
			else if (levelDifference < 0)
			{
				int absoluteDifference = Math.abs(levelDifference);
				double penalty = absoluteDifference * 0.2;
				levelMultiplier = Math.max(0.0, levelMultiplier - penalty);
			}

			long baseLampExp = (long) (MagicLampConfig.MAGIC_LAMP_MAX_LEVEL_EXP * baseContributionPercentage);
			long lampExp = (long) (Math.max(exp, baseLampExp) * levelMultiplier * player.getStat().getExpBonusMultiplier() * (rateModifiers ? MagicLampConfig.MAGIC_LAMP_CHARGE_RATE * player.getStat().getMul(Stat.MAGIC_LAMP_EXP_RATE, 1.0) : 1.0));
			long totalLampExp = lampExp + player.getLampExp();
			if (totalLampExp >= MagicLampConfig.MAGIC_LAMP_MAX_LEVEL_EXP)
			{
				totalLampExp %= MagicLampConfig.MAGIC_LAMP_MAX_LEVEL_EXP;
				this.useMagicLamp(player);
			}

			player.setLampExp((int) totalLampExp);
			player.sendPacket(new ExMagicLampInfo(player));
		}
	}

	private static void manageSkill(Player player, LampType lampType)
	{
		Skill lampSkill = switch (lampType)
		{
			case RED -> CommonSkill.RED_LAMP.getSkill();
			case PURPLE -> CommonSkill.PURPLE_LAMP.getSkill();
			case BLUE -> CommonSkill.BLUE_LAMP.getSkill();
			case GREEN -> CommonSkill.GREEN_LAMP.getSkill();
			default -> null;
		};
		if (lampSkill != null)
		{
			player.breakAttack();
			player.breakCast();
			player.doCast(lampSkill);
		}
	}

	public static MagicLampManager getInstance()
	{
		return MagicLampManager.SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final MagicLampManager INSTANCE = new MagicLampManager();
	}
}
