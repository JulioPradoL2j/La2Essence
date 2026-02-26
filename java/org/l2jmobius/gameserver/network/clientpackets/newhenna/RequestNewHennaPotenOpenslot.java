package org.l2jmobius.gameserver.network.clientpackets.newhenna;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.henna.HennaPoten;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import org.l2jmobius.gameserver.network.serverpackets.newhenna.NewHennaPotenOpenslot;

public class RequestNewHennaPotenOpenslot extends ClientPacket
{
	private int _slotId;
	private int _reqOpenSlotStep;

	@Override
	protected void readImpl()
	{
		this._slotId = this.readInt();
		this._reqOpenSlotStep = this.readInt();
		this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			HennaPoten henna = player.getHennaPotenList()[this._slotId - 1];
			if (this._reqOpenSlotStep > 0 && this._reqOpenSlotStep < 31)
			{
				if (henna.getUnlockSlot() + 1 == this._reqOpenSlotStep)
				{
					henna.setUnlockSlot(this._reqOpenSlotStep);
					player.sendPacket(new NewHennaPotenOpenslot(true, this._slotId, this._reqOpenSlotStep, henna.getActiveStep()));
					player.applyDyePotenSkills();
				}
				else
				{
					player.sendPacket(ActionFailed.STATIC_PACKET);
					player.sendPacket(new NewHennaPotenOpenslot(false, this._slotId, this._reqOpenSlotStep, henna.getActiveStep()));
				}
			}
		}
	}
}
