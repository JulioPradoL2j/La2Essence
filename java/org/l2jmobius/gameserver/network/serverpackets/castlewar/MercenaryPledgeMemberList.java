package org.l2jmobius.gameserver.network.serverpackets.castlewar;

import java.util.Map;
import java.util.Map.Entry;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.sql.ClanTable;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.siege.MercenaryPledgeHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class MercenaryPledgeMemberList extends ServerPacket
{
	private final int _castleId;
	private final int _clanId;
	private final Map<Integer, MercenaryPledgeHolder> _mercenaries;

	public MercenaryPledgeMemberList(int castleId, int clanId)
	{
		this._castleId = castleId;
		this._clanId = clanId;
		this._mercenaries = ClanTable.getInstance().getClan(this._clanId).getMapMercenary();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_MERCENARY_MEMBER_LIST.writeId(this, buffer);
		buffer.writeInt(this._castleId);
		buffer.writeInt(this._clanId);
		buffer.writeInt(this._mercenaries.size());

		for (Entry<Integer, MercenaryPledgeHolder> entry : this._mercenaries.entrySet())
		{
			Player player = World.getInstance().getPlayer(entry.getKey());
			MercenaryPledgeHolder mercenary = entry.getValue();
			buffer.writeInt(entry.getKey() == mercenary.getPlayerId());
			if (player == null)
			{
				buffer.writeInt(0);
			}
			else
			{
				buffer.writeInt(player.isOnline());
			}

			buffer.writeSizedString(mercenary.getName());
			buffer.writeInt(mercenary.getClassId());
		}
	}
}
