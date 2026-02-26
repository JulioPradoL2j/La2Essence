package org.l2jmobius.gameserver.network.serverpackets.huntpass;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.HuntPass;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class HuntPassInfo extends ServerPacket
{
	private final int _interfaceType;
	private final HuntPass _huntPass;
	private final int _timeEnd;
	private final boolean _isPremium;
	private final int _points;
	private final int _step;
	private final int _rewardStep;
	private final int _premiumRewardStep;

	public HuntPassInfo(Player player, int interfaceType)
	{
		this._interfaceType = interfaceType;
		this._huntPass = player.getHuntPass();
		this._timeEnd = this._huntPass.getHuntPassDayEnd();
		this._isPremium = this._huntPass.isPremium();
		this._points = this._huntPass.getPoints();
		this._step = this._huntPass.getCurrentStep();
		this._rewardStep = this._huntPass.getRewardStep();
		this._premiumRewardStep = this._huntPass.getPremiumRewardStep();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_L2PASS_INFO.writeId(this, buffer);
		buffer.writeByte(this._interfaceType);
		buffer.writeInt(this._timeEnd);
		buffer.writeByte(this._isPremium);
		buffer.writeInt(this._points);
		buffer.writeInt(this._step);
		buffer.writeInt(this._rewardStep);
		buffer.writeInt(this._premiumRewardStep);
	}
}
