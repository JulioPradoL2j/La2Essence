package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExResponseBeautyRegistReset extends ServerPacket
{
	public static final int FAILURE = 0;
	public static final int SUCCESS = 1;
	public static final int CHANGE = 0;
	public static final int RESTORE = 1;
	private final Player _player;
	private final int _type;
	private final int _result;

	public ExResponseBeautyRegistReset(Player player, int type, int result)
	{
		this._player = player;
		this._type = type;
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESPONSE_BEAUTY_REGIST_RESET.writeId(this, buffer);
		buffer.writeLong(this._player.getAdena());
		buffer.writeLong(this._player.getBeautyTickets());
		buffer.writeInt(this._type);
		buffer.writeInt(this._result);
		buffer.writeInt(this._player.getVisualHair());
		buffer.writeInt(this._player.getVisualFace());
		buffer.writeInt(this._player.getVisualHairColor());
	}
}
