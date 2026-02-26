package org.l2jmobius.gameserver.network.clientpackets.commission;

import java.util.function.Predicate;

import org.l2jmobius.gameserver.managers.ItemCommissionManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.commission.CommissionItemType;
import org.l2jmobius.gameserver.model.commission.CommissionTreeType;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.item.type.CrystalType;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.commission.ExCloseCommission;

public class RequestCommissionList extends ClientPacket
{
	private int _treeViewDepth;
	private int _itemType;
	private int _type;
	private int _grade;
	private String _query;

	@Override
	protected void readImpl()
	{
		this._treeViewDepth = this.readInt();
		this._itemType = this.readInt();
		this._type = this.readInt();
		this._grade = this.readInt();
		this._query = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (!ItemCommissionManager.isPlayerAllowedToInteract(player))
			{
				player.sendPacket(ExCloseCommission.STATIC_PACKET);
			}
			else
			{
				Predicate<ItemTemplate> filter = _ -> true;
				switch (this._treeViewDepth)
				{
					case 1:
						CommissionTreeType commissionTreeType = CommissionTreeType.findByClientId(this._itemType);
						if (commissionTreeType != null)
						{
							filter = filter.and(i -> commissionTreeType.getCommissionItemTypes().contains(i.getCommissionItemType()));
						}
						break;
					case 2:
						CommissionItemType commissionItemType = CommissionItemType.findByClientId(this._itemType);
						if (commissionItemType != null)
						{
							filter = filter.and(i -> i.getCommissionItemType() == commissionItemType);
						}
				}

				switch (this._type)
				{
					case 0:
						filter = filter.and(_ -> true);
						break;
					case 1:
						filter = filter.and(_ -> true);
				}

				switch (this._grade)
				{
					case 0:
						filter = filter.and(i -> i.getCrystalType() == CrystalType.NONE);
						break;
					case 1:
						filter = filter.and(i -> i.getCrystalType() == CrystalType.D);
						break;
					case 2:
						filter = filter.and(i -> i.getCrystalType() == CrystalType.C);
						break;
					case 3:
						filter = filter.and(i -> i.getCrystalType() == CrystalType.B);
						break;
					case 4:
						filter = filter.and(i -> i.getCrystalType() == CrystalType.A);
						break;
					case 5:
						filter = filter.and(i -> i.getCrystalType() == CrystalType.S);
						break;
					case 6:
						filter = filter.and(i -> i.getCrystalType() == CrystalType.S80);
						break;
					case 7:
						filter = filter.and(i -> i.getCrystalType() == CrystalType.R);
						break;
					case 8:
						filter = filter.and(i -> i.getCrystalType() == CrystalType.R95);
						break;
					case 9:
						filter = filter.and(i -> i.getCrystalType() == CrystalType.R99);
				}

				filter = filter.and(i -> this._query.isEmpty() || i.getName().toLowerCase().contains(this._query.toLowerCase()));
				ItemCommissionManager.getInstance().showAuctions(player, filter);
			}
		}
	}
}
