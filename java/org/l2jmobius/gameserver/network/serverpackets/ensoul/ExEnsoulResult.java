package org.l2jmobius.gameserver.network.serverpackets.ensoul;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.ensoul.EnsoulOption;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExEnsoulResult extends ServerPacket
{
	private final int _success;
	private final Collection<EnsoulOption> _specialAbilities;
	private final Collection<EnsoulOption> _additionalSpecialAbilities;

	public ExEnsoulResult(int success, Item item)
	{
		this._success = success;
		this._specialAbilities = item.getSpecialAbilities();
		this._additionalSpecialAbilities = item.getAdditionalSpecialAbilities();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ENSOUL_RESULT.writeId(this, buffer);
		buffer.writeByte(this._success);
		buffer.writeByte(this._specialAbilities.size());

		for (EnsoulOption option : this._specialAbilities)
		{
			buffer.writeInt(option.getId());
		}

		buffer.writeByte(this._additionalSpecialAbilities.size());

		for (EnsoulOption option : this._additionalSpecialAbilities)
		{
			buffer.writeInt(option.getId());
		}
	}
}
