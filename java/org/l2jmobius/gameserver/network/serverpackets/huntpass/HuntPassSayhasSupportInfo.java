package org.l2jmobius.gameserver.network.serverpackets.huntpass;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.HuntPass;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class HuntPassSayhasSupportInfo extends ServerPacket
{
	private final HuntPass _huntPass;
	private final int _timeUsed;
	private final boolean _sayhaToggle;

	public HuntPassSayhasSupportInfo(Player player)
	{
		this._huntPass = player.getHuntPass();
		this._sayhaToggle = this._huntPass.toggleSayha();
		this._timeUsed = this._huntPass.getUsedSayhaTime() + (int) (this._huntPass.getToggleStartTime() > 0 ? System.currentTimeMillis() / 1000L - this._huntPass.getToggleStartTime() : 0L);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SAYHAS_SUPPORT_INFO.writeId(this, buffer);
		buffer.writeByte(this._sayhaToggle);
		buffer.writeInt(this._huntPass.getAvailableSayhaTime());
		buffer.writeInt(this._timeUsed);
	}
}
