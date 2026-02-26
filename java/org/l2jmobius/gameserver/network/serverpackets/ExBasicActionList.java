package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.ActionData;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExBasicActionList extends ServerPacket
{
	public static final ExBasicActionList STATIC_PACKET = new ExBasicActionList(ActionData.getInstance().getActionIdList());
	private final int[] _actionIds;

	public ExBasicActionList(int[] actionIds)
	{
		this._actionIds = actionIds;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BASIC_ACTION_LIST.writeId(this, buffer);
		buffer.writeInt(this._actionIds.length);

		for (int actionId : this._actionIds)
		{
			buffer.writeInt(actionId);
		}
	}
}
