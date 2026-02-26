package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExShowChannelingEffect extends AbstractItemPacket
{
	private final Creature _caster;
	private final Creature _target;
	private final int _state;

	public ExShowChannelingEffect(Creature caster, Creature target, int state)
	{
		this._caster = caster;
		this._target = target;
		this._state = state;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_CHANNELING_EFFECT.writeId(this, buffer);
		buffer.writeInt(this._caster.getObjectId());
		buffer.writeInt(this._target.getObjectId());
		buffer.writeInt(this._state);
	}
}
