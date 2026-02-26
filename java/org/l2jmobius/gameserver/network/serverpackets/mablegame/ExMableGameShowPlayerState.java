package org.l2jmobius.gameserver.network.serverpackets.mablegame;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.MableGameData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExMableGameShowPlayerState extends ServerPacket
{
	private final int _commonDiceLimit;
	private final int _dailyAvailableRounds;
	private final int _highestCellId;
	private final ItemHolder _finishReward;
	private final List<ItemHolder> _resetItems;
	private final MableGameData.MableGamePlayerState _playerState;

	public ExMableGameShowPlayerState(Player player)
	{
		MableGameData data = MableGameData.getInstance();
		this._commonDiceLimit = data.getCommonDiceLimit();
		this._dailyAvailableRounds = data.getDailyAvailableRounds();
		this._highestCellId = data.getHighestCellId();
		this._finishReward = data.getRoundReward();
		this._resetItems = data.getResetItems();
		this._playerState = data.getPlayerState(player.getAccountName());
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MABLE_GAME_SHOW_PLAYER_STATE.writeId(this, buffer);
		buffer.writeInt(this._playerState.getRound());
		buffer.writeInt(this._playerState.getCurrentCellId());
		buffer.writeInt(this._playerState.getRemainCommonDice());
		buffer.writeInt(this._commonDiceLimit);
		buffer.writeByte(this._playerState.getCurrentCellId() == this._highestCellId ? (this._dailyAvailableRounds == this._playerState.getRound() ? 6 : 5) : 0);
		buffer.writeInt(this._dailyAvailableRounds);

		for (int i = 1; i <= this._dailyAvailableRounds; i++)
		{
			buffer.writeInt(i);
			buffer.writeInt(this._finishReward.getId());
			buffer.writeLong(this._finishReward.getCount());
		}

		buffer.writeInt(this._resetItems.size());

		for (ItemHolder item : this._resetItems)
		{
			buffer.writeInt(item.getId());
			buffer.writeLong(item.getCount());
		}
	}
}
