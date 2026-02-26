package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;
import java.util.Collections;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.skill.SkillCastingType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class MagicSkillLaunched extends ServerPacket
{
	private final int _objectId;
	private final int _skillId;
	private final int _skillLevel;
	private final SkillCastingType _castingType;
	private final Collection<WorldObject> _targets;

	public MagicSkillLaunched(Creature creature, int skillId, int skillLevel, SkillCastingType castingType, Collection<WorldObject> targets)
	{
		this._objectId = creature.getObjectId();
		this._skillId = skillId;
		this._skillLevel = skillLevel;
		this._castingType = castingType;
		if (targets == null)
		{
			this._targets = Collections.singletonList(creature);
		}
		else
		{
			this._targets = targets;
		}
	}

	public MagicSkillLaunched(Creature creature, int skillId, int skillLevel, SkillCastingType castingType, WorldObject target)
	{
		this(creature, skillId, skillLevel, castingType, Collections.singletonList((WorldObject) (target == null ? creature : target)));
	}

	public MagicSkillLaunched(Creature creature, int skillId, int skillLevel)
	{
		this(creature, skillId, skillLevel, SkillCastingType.NORMAL, Collections.singletonList(creature));
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.MAGIC_SKILL_LAUNCHED.writeId(this, buffer);
		buffer.writeInt(this._castingType.getClientBarId());
		buffer.writeInt(this._objectId);
		buffer.writeInt(this._skillId);
		buffer.writeInt(this._skillLevel);
		buffer.writeInt(this._targets.size());

		for (WorldObject target : this._targets)
		{
			buffer.writeInt(target.getObjectId());
		}
	}
}
