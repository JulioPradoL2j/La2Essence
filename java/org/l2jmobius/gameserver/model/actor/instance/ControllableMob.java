package org.l2jmobius.gameserver.model.actor.instance;

import org.l2jmobius.gameserver.ai.ControllableMobAI;
import org.l2jmobius.gameserver.ai.CreatureAI;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.enums.creature.InstanceType;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;

public class ControllableMob extends Monster
{
	private boolean _isInvul;

	@Override
	public boolean isAggressive()
	{
		return true;
	}

	@Override
	public int getAggroRange()
	{
		return 500;
	}

	public ControllableMob(NpcTemplate template)
	{
		super(template);
		this.setInstanceType(InstanceType.ControllableMob);
	}

	@Override
	protected CreatureAI initAI()
	{
		return new ControllableMobAI(this);
	}

	@Override
	public void detachAI()
	{
	}

	@Override
	public boolean isInvul()
	{
		return this._isInvul;
	}

	@Override
	public void setInvul(boolean isInvul)
	{
		this._isInvul = isInvul;
	}

	@Override
	public boolean doDie(Creature killer)
	{
		if (!super.doDie(killer))
		{
			return false;
		}
		this.setAI(null);
		return true;
	}
}
