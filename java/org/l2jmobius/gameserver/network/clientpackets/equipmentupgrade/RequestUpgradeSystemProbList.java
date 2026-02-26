package org.l2jmobius.gameserver.network.clientpackets.equipmentupgrade;

import org.l2jmobius.gameserver.data.holders.EquipmentUpgradeNormalHolder;
import org.l2jmobius.gameserver.data.xml.EquipmentUpgradeNormalData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.equipmentupgrade.ExUpgradeSystemProbList;

public class RequestUpgradeSystemProbList extends ClientPacket
{
	private int _type;
	private int _upgradeId;

	@Override
	protected void readImpl()
	{
		this._type = this.readInt();
		this._upgradeId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			EquipmentUpgradeNormalHolder holder = EquipmentUpgradeNormalData.getInstance().getUpgrade(this._upgradeId);
			if (holder != null)
			{
				player.sendPacket(new ExUpgradeSystemProbList(this._type, this._upgradeId, holder.getChance(), holder.getChanceToReceiveBonusItems()));
			}
		}
	}
}
