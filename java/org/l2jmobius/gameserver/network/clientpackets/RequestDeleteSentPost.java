package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.managers.MailManager;
import org.l2jmobius.gameserver.managers.PunishmentManager;
import org.l2jmobius.gameserver.model.Message;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExChangePostState;

public class RequestDeleteSentPost extends ClientPacket
{
 
	int[] _msgIds = null;

	@Override
	protected void readImpl()
	{
		int count = this.readInt();
		if (count > 0 && count <= PlayerConfig.MAX_ITEM_IN_PACKET && count * 4 == this.remaining())
		{
			this._msgIds = new int[count];

			for (int i = 0; i < count; i++)
			{
				this._msgIds[i] = this.readInt();
			}
		}
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && this._msgIds != null && GeneralConfig.ALLOW_MAIL)
		{
			for (int msgId : this._msgIds)
			{
				Message msg = MailManager.getInstance().getMessage(msgId);
				if (msg != null)
				{
					if (msg.getSenderId() != player.getObjectId())
					{
						PunishmentManager.handleIllegalPlayerAction(player, player + " tried to delete not own post!", GeneralConfig.DEFAULT_PUNISH);
						return;
					}

					if (msg.hasAttachments() || msg.isDeletedBySender())
					{
						return;
					}

					msg.setDeletedBySender();
				}
			}

			player.sendPacket(new ExChangePostState(false, this._msgIds, 0));
		}
	}
}
