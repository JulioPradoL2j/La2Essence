package org.l2jmobius.gameserver.network.serverpackets;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.MailManager;
import org.l2jmobius.gameserver.model.Message;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExShowSentPostList extends ServerPacket
{
	private final List<Message> _outbox;

	public ExShowSentPostList(int objectId)
	{
		this._outbox = MailManager.getInstance().getOutbox(objectId);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_SENT_POST_LIST.writeId(this, buffer);
		buffer.writeInt((int) (System.currentTimeMillis() / 1000L));
		if (this._outbox != null && !this._outbox.isEmpty())
		{
			buffer.writeInt(this._outbox.size());

			for (Message msg : this._outbox)
			{
				buffer.writeInt(msg.getId());
				buffer.writeString(msg.getSubject());
				buffer.writeString(msg.getReceiverName());
				buffer.writeInt(msg.isLocked());
				buffer.writeInt(msg.getExpirationSeconds());
				buffer.writeInt(msg.isUnread());
				buffer.writeInt(1);
				buffer.writeInt(msg.hasAttachments());
				buffer.writeInt(0);
			}
		}
		else
		{
			buffer.writeInt(0);
		}
	}
}
