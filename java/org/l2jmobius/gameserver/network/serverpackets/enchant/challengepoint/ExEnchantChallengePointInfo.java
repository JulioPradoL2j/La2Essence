package org.l2jmobius.gameserver.network.serverpackets.enchant.challengepoint;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.ChallengePointInfoHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExEnchantChallengePointInfo extends ServerPacket
{
	private final ChallengePointInfoHolder[] _challengeinfo;

	public ExEnchantChallengePointInfo(Player player)
	{
		this._challengeinfo = player.getChallengeInfo().initializeChallengePoints();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ENCHANT_CHALLENGE_POINT_INFO.writeId(this, buffer);
		buffer.writeInt(this._challengeinfo.length);

		for (ChallengePointInfoHolder info : this._challengeinfo)
		{
			buffer.writeInt(info.getPointGroupId());
			buffer.writeInt(info.getChallengePoint());
			buffer.writeInt(info.getTicketPointOpt1());
			buffer.writeInt(info.getTicketPointOpt2());
			buffer.writeInt(info.getTicketPointOpt3());
			buffer.writeInt(info.getTicketPointOpt4());
			buffer.writeInt(info.getTicketPointOpt5());
			buffer.writeInt(info.getTicketPointOpt6());
		}
	}
}
