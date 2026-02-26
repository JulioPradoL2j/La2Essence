package org.l2jmobius.gameserver.network.serverpackets.vip;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.VipSystemConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.vip.VipManager;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ReceiveVipInfo extends ServerPacket
{
	private final byte _vipTier;
	private final int _vipDuration;
	private final long _vipPoints;
	private final long _nextLevelPoints;
	private final long _currentLevelPoints;
	private final long _previousLevelPoints;

	public ReceiveVipInfo(Player player)
	{
		VipManager vipManager = VipManager.getInstance();
		this._vipTier = player.getVipTier();
		this._vipPoints = player.getVipPoints();
		this._vipDuration = (int) ChronoUnit.SECONDS.between(Instant.now(), Instant.ofEpochMilli(player.getVipTierExpiration()));
		this._nextLevelPoints = vipManager.getPointsToLevel((byte) (this._vipTier + 1));
		this._currentLevelPoints = vipManager.getPointsToLevel(this._vipTier);
		this._previousLevelPoints = vipManager.getPointsDepreciatedOnLevel(this._vipTier);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		if (VipSystemConfig.VIP_SYSTEM_ENABLED)
		{
			ServerPackets.EX_VIP_INFO.writeId(this, buffer);
			buffer.writeByte(this._vipTier);
			buffer.writeLong(this._vipPoints);
			buffer.writeInt(this._vipDuration);
			buffer.writeLong(this._nextLevelPoints);
			buffer.writeLong(this._previousLevelPoints);
			buffer.writeByte(this._vipTier);
			buffer.writeLong(this._currentLevelPoints);
		}
	}
}
