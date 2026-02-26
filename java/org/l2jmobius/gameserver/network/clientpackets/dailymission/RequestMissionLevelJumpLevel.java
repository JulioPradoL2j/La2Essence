package org.l2jmobius.gameserver.network.clientpackets.dailymission;

import org.l2jmobius.gameserver.data.xml.MissionLevel;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.MissionLevelPlayerDataHolder;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import org.l2jmobius.gameserver.network.serverpackets.dailymission.ExOneDayReceiveRewardList;

public class RequestMissionLevelJumpLevel extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		if (this.getClient().getFloodProtectors().canPerformPlayerAction())
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				if (player.destroyItemByItemId(ItemProcessType.FEE, 91663, 1000L, player, false))
				{
					MissionLevelPlayerDataHolder info = player.getMissionLevelProgress();
					info.setCurrentLevel(30);
					player.getVariables().set("MISSION_LEVEL_PROGRESS_" + MissionLevel.getInstance().getCurrentSeason(), info.getVariablesFromInfo());
					player.sendPacket(new ExOneDayReceiveRewardList(player, true));
				}
				else
				{
					player.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_L2_COINS_ADD_MORE_L2_COINS_AND_TRY_AGAIN);
					player.sendPacket(ActionFailed.STATIC_PACKET);
				}
			}
		}
	}
}
