package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Objects;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.groups.CommandChannel;
import org.l2jmobius.gameserver.model.groups.Party;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExMultiPartyCommandChannelInfo extends ServerPacket
{
	private final CommandChannel _channel;

	public ExMultiPartyCommandChannelInfo(CommandChannel channel)
	{
		Objects.requireNonNull(channel);
		this._channel = channel;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MULTI_PARTY_COMMAND_CHANNEL_INFO.writeId(this, buffer);
		buffer.writeString(this._channel.getLeader().getName());
		buffer.writeInt(0);
		buffer.writeInt(this._channel.getMemberCount());
		buffer.writeInt(this._channel.getParties().size());

		for (Party p : this._channel.getParties())
		{
			buffer.writeString(p.getLeader().getName());
			buffer.writeInt(p.getLeaderObjectId());
			buffer.writeInt(p.getMemberCount());
		}
	}
}
