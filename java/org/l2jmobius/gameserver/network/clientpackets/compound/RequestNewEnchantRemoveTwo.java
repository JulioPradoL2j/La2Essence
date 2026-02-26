package org.l2jmobius.gameserver.network.clientpackets.compound;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.CompoundRequest;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.compound.ExEnchantOneFail;
import org.l2jmobius.gameserver.network.serverpackets.compound.ExEnchantTwoRemoveFail;
import org.l2jmobius.gameserver.network.serverpackets.compound.ExEnchantTwoRemoveOK;

public class RequestNewEnchantRemoveTwo extends ClientPacket
{
	private int _objectId;

	@Override
	protected void readImpl()
	{
		this._objectId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (player.isInStoreMode())
			{
				player.sendPacket(SystemMessageId.YOU_CANNOT_DO_THAT_WHILE_IN_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
				player.sendPacket(ExEnchantOneFail.STATIC_PACKET);
			}
			else if (!player.isProcessingTransaction() && !player.isProcessingRequest())
			{
				CompoundRequest request = player.getRequest(CompoundRequest.class);
				if (request != null && !request.isProcessing())
				{
					Item item = request.getItemTwo();
					if (item != null && item.getObjectId() == this._objectId)
					{
						request.setItemTwo(0);
						if (request.getItemOne() == null)
						{
							player.removeRequest(request.getClass());
						}

						player.sendPacket(ExEnchantTwoRemoveOK.STATIC_PACKET);
					}
					else
					{
						player.sendPacket(ExEnchantTwoRemoveFail.STATIC_PACKET);
					}
				}
				else
				{
					player.sendPacket(ExEnchantTwoRemoveFail.STATIC_PACKET);
				}
			}
			else
			{
				player.sendPacket(SystemMessageId.YOU_CANNOT_USE_THIS_SYSTEM_DURING_TRADING_PRIVATE_STORE_AND_WORKSHOP_SETUP);
				player.sendPacket(ExEnchantOneFail.STATIC_PACKET);
			}
		}
	}
}
