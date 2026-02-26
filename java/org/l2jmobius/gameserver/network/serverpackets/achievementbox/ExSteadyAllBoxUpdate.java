package org.l2jmobius.gameserver.network.serverpackets.achievementbox;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.AchievementBoxHolder;
import org.l2jmobius.gameserver.model.actor.holders.player.AchievementBoxInfoHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExSteadyAllBoxUpdate extends ServerPacket
{
	private final AchievementBoxHolder _achievementBox;

	public ExSteadyAllBoxUpdate(Player player)
	{
		this._achievementBox = player.getAchievementBox();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_STEADY_ALL_BOX_UPDATE.writeId(this, buffer);
		buffer.writeInt(this._achievementBox.getMonsterPoints());
		buffer.writeInt(this._achievementBox.getPvpPoints());
		buffer.writeInt(this._achievementBox.getBoxOwned());

		for (int i = 1; i <= this._achievementBox.getBoxOwned(); i++)
		{
			AchievementBoxInfoHolder boxholder = this._achievementBox.getAchievementBox().get(i - 1);
			buffer.writeInt(i);
			buffer.writeInt(boxholder.getState().ordinal());
			buffer.writeInt(boxholder.getType().ordinal());
		}

		int rewardTimeStage = (int) ((this._achievementBox.getBoxOpenTime() - System.currentTimeMillis()) / 1000L);
		buffer.writeInt(rewardTimeStage > 0 ? rewardTimeStage : 0);
	}
}
