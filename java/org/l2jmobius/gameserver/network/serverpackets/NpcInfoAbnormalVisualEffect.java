package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Set;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.enums.creature.Team;
import org.l2jmobius.gameserver.model.skill.AbnormalVisualEffect;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class NpcInfoAbnormalVisualEffect extends ServerPacket
{
	private final Npc _npc;
	private final Set<AbnormalVisualEffect> _abnormalVisualEffects;
	private final Team _team;

	public NpcInfoAbnormalVisualEffect(Npc npc)
	{
		this._npc = npc;
		this._abnormalVisualEffects = this._npc.getEffectList().getCurrentAbnormalVisualEffects();
		this._team = GeneralConfig.BLUE_TEAM_ABNORMAL_EFFECT != null && GeneralConfig.RED_TEAM_ABNORMAL_EFFECT != null ? this._npc.getTeam() : Team.NONE;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.NPC_INFO_ABNORMAL_VISUAL_EFFECT.writeId(this, buffer);
		buffer.writeInt(this._npc.getObjectId());
		buffer.writeInt(this._npc.getTransformationDisplayId());
		buffer.writeShort(this._abnormalVisualEffects.size() + (this._team != Team.NONE ? 1 : 0));

		for (AbnormalVisualEffect abnormalVisualEffect : this._abnormalVisualEffects)
		{
			buffer.writeShort(abnormalVisualEffect.getClientId());
		}

		if (this._team == Team.BLUE)
		{
			if (GeneralConfig.BLUE_TEAM_ABNORMAL_EFFECT != null)
			{
				buffer.writeShort(GeneralConfig.BLUE_TEAM_ABNORMAL_EFFECT.getClientId());
			}
		}
		else if (this._team == Team.RED && GeneralConfig.RED_TEAM_ABNORMAL_EFFECT != null)
		{
			buffer.writeShort(GeneralConfig.RED_TEAM_ABNORMAL_EFFECT.getClientId());
		}
	}
}
