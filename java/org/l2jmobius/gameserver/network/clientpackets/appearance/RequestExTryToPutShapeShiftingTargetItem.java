package org.l2jmobius.gameserver.network.clientpackets.appearance;

import org.l2jmobius.gameserver.data.xml.AppearanceItemData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.ShapeShiftingItemRequest;
import org.l2jmobius.gameserver.model.item.appearance.AppearanceStone;
import org.l2jmobius.gameserver.model.item.enums.ItemLocation;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.itemcontainer.PlayerInventory;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.appearance.ExPutShapeShiftingTargetItemResult;

public class RequestExTryToPutShapeShiftingTargetItem extends ClientPacket
{
	private int _targetItemObjId;

	@Override
	protected void readImpl()
	{
		this._targetItemObjId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			ShapeShiftingItemRequest request = player.getRequest(ShapeShiftingItemRequest.class);
			if (request != null && !player.isInStoreMode() && !player.isCrafting() && !player.isProcessingRequest() && !player.isProcessingTransaction())
			{
				PlayerInventory inventory = player.getInventory();
				Item targetItem = inventory.getItemByObjectId(this._targetItemObjId);
				Item stone = request.getAppearanceStone();
				if (targetItem != null && stone != null)
				{
					if (stone.getOwnerId() != player.getObjectId() || targetItem.getOwnerId() != player.getObjectId())
					{
						player.sendPacket(ExPutShapeShiftingTargetItemResult.FAILED);
						player.removeRequest(ShapeShiftingItemRequest.class);
					}
					else if (!targetItem.getTemplate().isAppearanceable())
					{
						player.sendPacket(SystemMessageId.THIS_ITEM_CANNOT_BE_MODIFIED_OR_RESTORED);
						player.sendPacket(ExPutShapeShiftingTargetItemResult.FAILED);
					}
					else if (targetItem.getItemLocation() != ItemLocation.INVENTORY && targetItem.getItemLocation() != ItemLocation.PAPERDOLL)
					{
						player.sendPacket(ExPutShapeShiftingTargetItemResult.FAILED);
						player.removeRequest(ShapeShiftingItemRequest.class);
					}
					else if ((stone = inventory.getItemByObjectId(stone.getObjectId())) == null)
					{
						player.sendPacket(ExPutShapeShiftingTargetItemResult.FAILED);
						player.removeRequest(ShapeShiftingItemRequest.class);
					}
					else
					{
						AppearanceStone appearanceStone = AppearanceItemData.getInstance().getStone(stone.getId());
						if (appearanceStone == null)
						{
							player.sendPacket(ExPutShapeShiftingTargetItemResult.FAILED);
							player.removeRequest(ShapeShiftingItemRequest.class);
						}
						else if (!appearanceStone.checkConditions(player, targetItem))
						{
							player.sendPacket(ExPutShapeShiftingTargetItemResult.FAILED);
						}
						else
						{
							player.sendPacket(new ExPutShapeShiftingTargetItemResult(1, appearanceStone.getCost()));
						}
					}
				}
				else
				{
					player.sendPacket(ExPutShapeShiftingTargetItemResult.FAILED);
					player.removeRequest(ShapeShiftingItemRequest.class);
				}
			}
			else
			{
				player.sendPacket(ExPutShapeShiftingTargetItemResult.FAILED);
				player.sendPacket(SystemMessageId.YOU_CANNOT_USE_THIS_SYSTEM_DURING_TRADING_PRIVATE_STORE_AND_WORKSHOP_SETUP);
			}
		}
	}
}
