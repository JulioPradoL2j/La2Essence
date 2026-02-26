package org.l2jmobius.gameserver.model.actor.instance;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.model.actor.enums.creature.InstanceType;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;
import org.l2jmobius.gameserver.network.serverpackets.PlaySound;

public class RaidBoss extends Monster
{
	private boolean _useRaidCurse = true;

	public RaidBoss(NpcTemplate template)
	{
		super(template);
		this.setInstanceType(InstanceType.RaidBoss);
		this.setIsRaid(true);
		this.setLethalable(false);
	}

	@Override
	public void onSpawn()
	{
		super.onSpawn();
		this.setRandomWalking(false);
		this.broadcastPacket(new PlaySound(1, this.getParameters().getString("RaidSpawnMusic", "Rm01_A"), 0, 0, 0, 0, 0));
	}

	@Override
	public int getVitalityPoints(int level, double exp, boolean isBoss)
	{
		return -super.getVitalityPoints(level, exp, isBoss);
	}

	@Override
	public boolean useVitalityRate()
	{
		return PlayerConfig.RAIDBOSS_USE_VITALITY;
	}

	public void setUseRaidCurse(boolean value)
	{
		this._useRaidCurse = value;
	}

	@Override
	public boolean giveRaidCurse()
	{
		return this._useRaidCurse;
	}
}
