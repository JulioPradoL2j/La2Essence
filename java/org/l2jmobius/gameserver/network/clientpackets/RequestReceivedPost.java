package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.managers.MailManager;
import org.l2jmobius.gameserver.managers.PunishmentManager;
import org.l2jmobius.gameserver.model.Message;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExChangePostState;
import org.l2jmobius.gameserver.network.serverpackets.ExReplyReceivedPost;

public class RequestReceivedPost extends ClientPacket
{
	private int _msgId;

	@Override
	protected void readImpl()
	{
		this._msgId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && GeneralConfig.ALLOW_MAIL)
		{
			Message msg = MailManager.getInstance().getMessage(this._msgId);
			if (msg != null)
			{
				if (msg.getReceiverId() != player.getObjectId())
				{
					PunishmentManager.handleIllegalPlayerAction(player, player + " tried to receive not own post!", GeneralConfig.DEFAULT_PUNISH);
				}
				else if (!msg.isDeletedByReceiver())
				{
					player.sendPacket(new ExReplyReceivedPost(msg));
					player.sendPacket(new ExChangePostState(true, this._msgId, 1));
					msg.markAsRead();
				}
			}
		}
	}
}
