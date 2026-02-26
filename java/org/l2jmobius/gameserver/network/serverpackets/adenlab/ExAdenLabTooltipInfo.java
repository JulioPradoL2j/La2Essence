package org.l2jmobius.gameserver.network.serverpackets.adenlab;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAdenLabTooltipInfo extends ServerPacket
{
	private final Map<Integer, Integer> _pkAdenLabBossTooltip = new LinkedHashMap<>();

	public ExAdenLabTooltipInfo(Map<Integer, Integer> bossTooltips)
	{
		this._pkAdenLabBossTooltip.putAll(bossTooltips);
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ADENLAB_TOOLTIP_INFO.writeId(this, buffer);
		buffer.writeInt(this._pkAdenLabBossTooltip.size());

		for (Entry<Integer, Integer> entry : this._pkAdenLabBossTooltip.entrySet())
		{
			buffer.writeInt(entry.getKey());
			buffer.writeInt(entry.getValue());
		}
	}
}
