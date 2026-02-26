package org.l2jmobius.gameserver.network.serverpackets.equipmentupgrade;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExUpgradeSystemProbList extends ServerPacket
{
	private final int _type;
	private final int _upgradeId;
	private final double _chance;
	private final double _bonusChance;

	public ExUpgradeSystemProbList(int type, int upgradeId, double chance, double bonusChance)
	{
		this._type = type;
		this._upgradeId = upgradeId;
		this._chance = chance;
		this._bonusChance = bonusChance;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_UPGRADE_SYSTEM_PROB_LIST.writeId(this, buffer);
		buffer.writeInt(this._type);
		buffer.writeInt(this._upgradeId);
		buffer.writeInt((int) (this._chance * 10000.0));
		buffer.writeInt((int) (this._bonusChance * 10000.0));
	}
}
