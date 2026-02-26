package org.l2jmobius.gameserver.network.clientpackets.pet;

import org.l2jmobius.gameserver.ai.Intention;
import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.managers.FortSiegeManager;
import org.l2jmobius.gameserver.managers.SiegeGuardManager;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Pet;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;

public class RequestPetGetItem extends ClientPacket
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
		World world = World.getInstance();
		Item item = (Item) world.findObject(this._objectId);
		Player player = this.getPlayer();
		if (item != null && player != null && player.hasPet())
		{
			Castle castle = CastleManager.getInstance().getCastle(item);
			if (castle != null && SiegeGuardManager.getInstance().getSiegeGuardByItem(castle.getResidenceId(), item.getId()) != null)
			{
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else if (FortSiegeManager.getInstance().isCombat(item.getId()))
			{
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else
			{
				Pet pet = player.getPet();
				if (pet.isDead() || pet.isControlBlocked())
				{
					player.sendPacket(ActionFailed.STATIC_PACKET);
				}
				else if (pet.isUncontrollable())
				{
					player.sendPacket(SystemMessageId.WHEN_YOUR_GUARDIAN_S_SATIETY_REACHES_0_YOU_CANNOT_CONTROL_IT);
				}
				else
				{
					pet.getAI().setIntention(Intention.PICK_UP, item);
				}
			}
		}
		else
		{
			this.getClient().sendPacket(ActionFailed.STATIC_PACKET);
		}
	}
}
