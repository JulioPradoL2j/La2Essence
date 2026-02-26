package org.l2jmobius.gameserver.network.serverpackets.achievementbox;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.AchievementBoxHolder;
import org.l2jmobius.gameserver.model.actor.holders.player.AchievementBoxInfoHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExSteadyOneBoxUpdate extends ServerPacket
{
	private final AchievementBoxHolder _achievementBox;
	private final int _slotId;

	public ExSteadyOneBoxUpdate(Player player, int slotId)
	{
		this._achievementBox = player.getAchievementBox();
		this._slotId = slotId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_STEADY_ONE_BOX_UPDATE.writeId(this, buffer);
		buffer.writeInt(this._achievementBox.getMonsterPoints());
		buffer.writeInt(this._achievementBox.getPvpPoints());
		AchievementBoxInfoHolder boxholder = this._achievementBox.getAchievementBox().get(this._slotId - 1);
		buffer.writeInt(this._slotId);
		buffer.writeInt(boxholder.getState().ordinal());
		buffer.writeInt(boxholder.getType().ordinal());
		int rewardTimeStage = (int) ((this._achievementBox.getBoxOpenTime() - System.currentTimeMillis()) / 1000L);
		buffer.writeInt(rewardTimeStage > 0 ? rewardTimeStage : 0);
	}
}
