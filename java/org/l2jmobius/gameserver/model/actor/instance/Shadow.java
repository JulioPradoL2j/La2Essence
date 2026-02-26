package org.l2jmobius.gameserver.model.actor.instance;

import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;

import org.l2jmobius.commons.threads.ThreadPool;
import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.ai.Intention;
import org.l2jmobius.gameserver.data.xml.SkillData;
import org.l2jmobius.gameserver.model.actor.Attackable;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.creature.Team;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;
import org.l2jmobius.gameserver.model.effects.EffectFlag;
import org.l2jmobius.gameserver.model.olympiad.OlympiadGameManager;
import org.l2jmobius.gameserver.model.skill.BuffInfo;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ExDamagePopUp;
import org.l2jmobius.gameserver.network.serverpackets.ExMagicAttackInfo;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class Shadow extends Attackable
{
	private ScheduledFuture<?> _attackTask = null;
	private Creature _attackTarget = null;

	public Shadow(NpcTemplate template, Player owner)
	{
		super(template);
		this.setCloneObjId(owner.getObjectId());
		this.setClanId(owner.getClanId());
		this.setXYZInvisible(owner.getX() + Rnd.get(-100, 100), owner.getY() + Rnd.get(-100, 100), owner.getZ());
		this._attackTask = ThreadPool.scheduleAtFixedRate(new Shadow.skillRepeat(this, SkillData.getInstance().getSkill(87452, 1)), 2000L, 2000L);
	}

	@Override
	public boolean isAttackable()
	{
		return true;
	}

	@Override
	public boolean isTargetable()
	{
		return false;
	}

	@Override
	public boolean isShowName()
	{
		return false;
	}

	public boolean isShowHp()
	{
		return false;
	}

	@Override
	public void onSpawn()
	{
		super.onSpawn();
		Creature summoner = this.getSummoner();
		if (summoner != null)
		{
			for (BuffInfo info : summoner.getEffectList().getBuffs())
			{
				Skill skill = info.getSkill();
				if (skill != null && !this.isAffectedBySkill(skill.getId()) && !skill.hasNegativeEffect() && skill.isContinuous())
				{
					skill.applyEffects(this, this, false, info.getAbnormalTime());
				}
			}

			for (BuffInfo infox : summoner.getEffectList().getPassives())
			{
				Skill skill = infox.getSkill();
				if (skill != null && !this.isAffectedBySkill(skill.getId()) && skill.isPassive())
				{
					this.addSkill(skill);
				}
			}
		}
	}

	public void stopAttackTask()
	{
		if (this._attackTask != null && !this._attackTask.isCancelled() && !this._attackTask.isDone())
		{
			this._attackTask.cancel(false);
			this._attackTask = null;
			this._attackTarget = null;
		}
	}

	public void startAttackTask(Creature target)
	{
		this.stopAttackTask();
		this._attackTarget = target;
		this._attackTask = ThreadPool.scheduleAtFixedRate(this::thinkCombat, 1000L, 1000L);
	}

	private void thinkCombat()
	{
		if (this._attackTarget == null)
		{
			this.stopAttackTask();
		}
		else
		{
			this.doAutoAttack(this._attackTarget);
		}
	}

	@Override
	public byte getPvpFlag()
	{
		return this.getSummoner() != null ? this.getSummoner().getPvpFlag() : 0;
	}

	@Override
	public Team getTeam()
	{
		return this.getSummoner() != null ? this.getSummoner().getTeam() : Team.NONE;
	}

	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		return false;
	}

	@Override
	public void doAttack(double damage, Creature target, Skill skill, boolean isDOT, boolean directlyToHp, boolean critical, boolean reflect)
	{
		super.doAttack(damage, target, skill, isDOT, directlyToHp, critical, reflect);
		this.sendDamageMessage(target, skill, (int) damage, 0.0, critical, false, false);
	}

	@Override
	public void sendDamageMessage(Creature target, Skill skill, int damage, double elementalDamage, boolean crit, boolean miss, boolean elementalCrit)
	{
		if (!miss && this.getSummoner() != null && this.getSummoner().isPlayer())
		{
			if (target.getObjectId() != this.getSummoner().getObjectId())
			{
				Player player = this.asPlayer();
				if (player.isInOlympiadMode() && target.isPlayer() && target.asPlayer().isInOlympiadMode() && target.asPlayer().getOlympiadGameId() == player.getOlympiadGameId())
				{
					OlympiadGameManager.getInstance().notifyCompetitorDamage(this.getSummoner().asPlayer(), damage);
				}

				SystemMessage sm;
				if (target.isHpBlocked() && !target.isNpc() || target.isPlayer() && target.isAffected(EffectFlag.DUELIST_FURY) && !player.isAffected(EffectFlag.FACEOFF))
				{
					if (skill == null)
					{
						this.sendPacket(new ExDamagePopUp(this.getObjectId(), target.getObjectId(), 0, (byte) 12));
					}
					else
					{
						this.sendPacket(new ExMagicAttackInfo(this.getObjectId(), target.getObjectId(), 4));
					}

					sm = new SystemMessage(SystemMessageId.THE_ATTACK_HAS_BEEN_BLOCKED);
				}
				else
				{
					if (crit)
					{
						this.sendPacket(new ExDamagePopUp(this.getObjectId(), target.getObjectId(), damage, (byte) 3));
						target.sendPacket(new ExDamagePopUp(this.getObjectId(), target.getObjectId(), damage, (byte) 3));
					}
					else if (skill != null)
					{
						this.sendPacket(new ExDamagePopUp(this.getObjectId(), target.getObjectId(), damage, (byte) 13));
						target.sendPacket(new ExDamagePopUp(this.getObjectId(), target.getObjectId(), damage, (byte) 13));
					}
					else
					{
						this.sendPacket(new ExDamagePopUp(this.getObjectId(), target.getObjectId(), damage, (byte) 1));
						target.sendPacket(new ExDamagePopUp(this.getObjectId(), target.getObjectId(), damage, (byte) 1));
					}

					sm = new SystemMessage(SystemMessageId.C1_HAS_DEALT_S3_DAMAGE_TO_C2);
					sm.addNpcName(this);
					sm.addString(target.getName());
					sm.addInt(damage);
					sm.addPopup(target.getObjectId(), this.getObjectId(), damage * -1);
				}

				this.sendPacket(sm);
			}
		}
		else
		{
			this.sendPacket(new ExDamagePopUp(this.getObjectId(), target.getObjectId(), 0, (byte) 11));
		}
	}

	@Override
	public void reduceCurrentHp(double damage, Creature attacker, Skill skill)
	{
		super.reduceCurrentHp(damage, attacker, skill);
		if (this.getSummoner() != null && this.getSummoner().isPlayer() && attacker != null && !this.isDead() && !this.isHpBlocked())
		{
			SystemMessage sm = new SystemMessage(SystemMessageId.C1_HAS_RECEIVED_S3_DAMAGE_FROM_C2);
			sm.addNpcName(this);
			sm.addString(attacker.getName());
			sm.addInt((int) damage);
			sm.addPopup(this.getObjectId(), attacker.getObjectId(), (int) (-damage));
			this.sendPacket(sm);
		}
	}

	@Override
	public Player asPlayer()
	{
		return this.getSummoner() != null ? this.getSummoner().asPlayer() : super.asPlayer();
	}

	@Override
	public boolean deleteMe()
	{
		this.stopAttackTask();
		return super.deleteMe();
	}

	@Override
	public synchronized void onTeleported()
	{
		this.deleteMe();
	}

	@Override
	public void sendPacket(ServerPacket packet)
	{
		if (this.getSummoner() != null)
		{
			this.getSummoner().sendPacket(packet);
		}
	}

	@Override
	public void sendPacket(SystemMessageId id)
	{
		if (this.getSummoner() != null)
		{
			this.getSummoner().sendPacket(id);
		}
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("(");
		sb.append(this.getId());
		sb.append(") Summoner: ");
		sb.append(this.getSummoner());
		return sb.toString();
	}

	private static class skillRepeat implements Runnable
	{
		private final Shadow _shadow;
		private final Skill _skill;

		skillRepeat(Shadow shadow, Skill attack)
		{
			this._shadow = shadow;
			this._skill = attack;
		}

		@Override
		public void run()
		{
			try
			{
				this._shadow.getAI().setIntention(Intention.IDLE);
				this._shadow.setTarget(this._shadow);
				this._shadow.doCast(this._skill);
			}
			catch (Throwable var2)
			{
				Creature.LOGGER.log(Level.SEVERE, "Shadow Error: ", var2);
			}
		}
	}
}
