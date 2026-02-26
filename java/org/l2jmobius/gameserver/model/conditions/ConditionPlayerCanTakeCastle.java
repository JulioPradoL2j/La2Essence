package org.l2jmobius.gameserver.model.conditions;

import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.util.LocationUtil;

public class ConditionPlayerCanTakeCastle extends Condition
{
	private final boolean _value;

	public ConditionPlayerCanTakeCastle(boolean value)
	{
		this._value = value;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item)
	{
		if (effector != null && effector.isPlayer())
		{
			Player player = effector.asPlayer();
			boolean canTakeCastle = true;
			if (player.isAlikeDead() || player.isCursedWeaponEquipped() || !player.isClanLeader())
			{
				canTakeCastle = false;
			}

			Castle castle = CastleManager.getInstance().getCastle(player);
			if (castle == null || castle.getResidenceId() <= 0 || !castle.getSiege().isInProgress() || castle.getSiege().getAttackerClan(player.getClan()) == null)
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.S1_CANNOT_BE_USED_THE_REQUIREMENTS_ARE_NOT_MET);
				sm.addSkillName(skill);
				player.sendPacket(sm);
				canTakeCastle = false;
			}
			else if (!castle.getArtefacts().contains(effected))
			{
				player.sendPacket(SystemMessageId.INVALID_TARGET);
				canTakeCastle = false;
			}
			else if (!LocationUtil.checkIfInRange(200, player, effected, true) || player.getZ() < effected.getZ() || Math.abs(player.getZ() - effected.getZ()) > 40)
			{
				player.sendPacket(SystemMessageId.THE_DISTANCE_IS_TOO_FAR_AND_SO_THE_CASTING_HAS_BEEN_CANCELLED);
				canTakeCastle = false;
			}

			return this._value == canTakeCastle;
		}
		return !this._value;
	}
}
