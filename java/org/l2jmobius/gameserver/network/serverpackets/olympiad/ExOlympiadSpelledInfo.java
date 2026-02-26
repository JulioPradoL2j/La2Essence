package org.l2jmobius.gameserver.network.serverpackets.olympiad;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.skill.BuffInfo;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExOlympiadSpelledInfo extends ServerPacket
{
	private final int _playerId;
	private final List<BuffInfo> _effects = new ArrayList<>();
	private final List<Skill> _effects2 = new ArrayList<>();

	public ExOlympiadSpelledInfo(Player player)
	{
		this._playerId = player.getObjectId();
	}

	public void addSkill(BuffInfo info)
	{
		this._effects.add(info);
	}

	public void addSkill(Skill skill)
	{
		this._effects2.add(skill);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_OLYMPIAD_SPELLED_INFO.writeId(this, buffer);
		buffer.writeInt(this._playerId);
		buffer.writeInt(this._effects.size() + this._effects2.size());

		for (BuffInfo info : this._effects)
		{
			if (info != null && info.isInUse())
			{
				buffer.writeInt(info.getSkill().getDisplayId());
				buffer.writeShort(info.getSkill().getDisplayLevel());
				buffer.writeShort(0);
				buffer.writeInt(info.getSkill().getAbnormalType().getClientId());
				this.writeOptionalInt(info.getSkill().isAura() ? -1 : info.getTime(), buffer);
			}
		}

		for (Skill skill : this._effects2)
		{
			if (skill != null)
			{
				buffer.writeInt(skill.getDisplayId());
				buffer.writeShort(skill.getDisplayLevel());
				buffer.writeShort(0);
				buffer.writeInt(skill.getAbnormalType().getClientId());
				buffer.writeShort(-1);
			}
		}
	}
}
