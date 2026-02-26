package org.l2jmobius.gameserver.model.instancezone.conditions;

import org.l2jmobius.gameserver.model.StatSet;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.instancezone.InstanceTemplate;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.network.SystemMessageId;

public class ConditionItem extends Condition
{
	private final int _itemId;
	private final long _count;
	private final boolean _take;

	public ConditionItem(InstanceTemplate template, StatSet parameters, boolean onlyLeader, boolean showMessageAndHtml)
	{
		super(template, parameters, onlyLeader, showMessageAndHtml);
		this._itemId = parameters.getInt("id");
		this._count = parameters.getLong("count");
		this._take = parameters.getBoolean("take", false);
		this.setSystemMessage(SystemMessageId.C1_DOES_NOT_MEET_ITEM_REQUIREMENTS_AND_CANNOT_ENTER, (msg, player) -> msg.addString(player.getName()));
	}

	@Override
	protected boolean test(Player player, Npc npc)
	{
		return player.getInventory().getInventoryItemCount(this._itemId, -1) >= this._count;
	}

	@Override
	protected void onSuccess(Player player)
	{
		if (this._take)
		{
			player.destroyItemByItemId(ItemProcessType.FEE, this._itemId, this._count, null, true);
		}
	}
}
