package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.data.xml.SkillData;
import org.l2jmobius.gameserver.data.xml.SkillTreeData;
import org.l2jmobius.gameserver.model.SkillLearn;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.creature.Race;
import org.l2jmobius.gameserver.model.clan.ClanAccess;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.skill.enums.AcquireSkillType;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.serverpackets.AcquireSkillInfo;
import org.l2jmobius.gameserver.network.serverpackets.ExAcquireSkillInfo;

public class RequestAcquireSkillInfo extends ClientPacket
{
	private int _id;
	private int _level;
	private AcquireSkillType _skillType;

	@Override
	protected void readImpl()
	{
		this._id = this.readInt();
		this._level = this.readInt();
		this._skillType = AcquireSkillType.getAcquireSkillType(this.readInt());
	}

	@Override
	protected void runImpl()
	{
		if (this._id > 0 && this._level > 0)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				Npc trainer = player.getLastFolkNPC();
				if (this._skillType == AcquireSkillType.CLASS || trainer != null && trainer.isNpc() && (trainer.canInteract(player) || player.isGM()))
				{
					this._id = player.getOriginalSkill(this._id);
					Skill skill = SkillData.getInstance().getSkill(this._id, this._level);
					if (skill == null)
					{
						PacketLogger.warning("Skill Id: " + this._id + " level: " + this._level + " is undefined. " + RequestAcquireSkillInfo.class.getName() + " failed.");
					}
					else
					{
						SkillLearn s = SkillTreeData.getInstance().getSkillLearn(this._skillType, this._id, this._level, player);
						if (s != null)
						{
							switch (this._skillType)
							{
								case TRANSFORM:
								case FISHING:
								case SUBCLASS:
								case COLLECT:
								case TRANSFER:
								case DUALCLASS:
									player.sendPacket(new AcquireSkillInfo(player, this._skillType, s));
									break;
								case CLASS:
									player.sendPacket(new ExAcquireSkillInfo(player, s));
									break;
								case PLEDGE:
									if (!player.isClanLeader())
									{
										return;
									}

									player.sendPacket(new AcquireSkillInfo(player, this._skillType, s));
									break;
								case SUBPLEDGE:
									if (!player.isClanLeader() || !player.hasAccess(ClanAccess.MEMBER_FAME))
									{
										return;
									}

									player.sendPacket(new AcquireSkillInfo(player, this._skillType, s));
									break;
								case ALCHEMY:
									if (player.getRace() != Race.ERTHEIA)
									{
										return;
									}

									player.sendPacket(new AcquireSkillInfo(player, this._skillType, s));
									break;
								case REVELATION:
									return;
								case REVELATION_DUALCLASS:
									return;
							}
						}
					}
				}
			}
		}
		else
		{
			PacketLogger.warning(RequestAcquireSkillInfo.class.getSimpleName() + ": Invalid Id: " + this._id + " or level: " + this._level + "!");
		}
	}
}
