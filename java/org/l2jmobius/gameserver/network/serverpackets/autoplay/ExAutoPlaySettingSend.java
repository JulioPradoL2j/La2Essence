package org.l2jmobius.gameserver.network.serverpackets.autoplay;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAutoPlaySettingSend extends ServerPacket
{
	private final int _options;
	private final boolean _active;
	private final boolean _pickUp;
	private final int _nextTargetMode;
	private final boolean _shortRange;
	private final int _potionPercent;
	private final boolean _respectfulHunting;
	private final int _petPotionPercent;

	public ExAutoPlaySettingSend(int options, boolean active, boolean pickUp, int nextTargetMode, boolean shortRange, int potionPercent, boolean respectfulHunting, int petPotionPercent)
	{
		this._options = options;
		this._active = active;
		this._pickUp = pickUp;
		this._nextTargetMode = nextTargetMode;
		this._shortRange = shortRange;
		this._potionPercent = potionPercent;
		this._respectfulHunting = respectfulHunting;
		this._petPotionPercent = petPotionPercent;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_AUTOPLAY_SETTING.writeId(this, buffer);
		buffer.writeShort(this._options);
		buffer.writeByte(this._active);
		buffer.writeByte(this._pickUp);
		buffer.writeShort(this._nextTargetMode);
		buffer.writeByte(this._shortRange);
		buffer.writeInt(this._potionPercent);
		buffer.writeInt(this._petPotionPercent);
		buffer.writeByte(this._respectfulHunting);
		buffer.writeByte(0);
	}
}
