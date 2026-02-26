package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.enums.HtmlActionScope;

public class ExPremiumManagerShowHtml extends AbstractHtmlPacket
{
	public ExPremiumManagerShowHtml(String html)
	{
		super(html);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PREMIUM_MANAGER_SHOW_HTML.writeId(this, buffer);
		buffer.writeInt(this.getNpcObjId());
		buffer.writeString(this.getHtml());
		buffer.writeInt(-1);
		buffer.writeInt(0);
	}

	@Override
	public HtmlActionScope getScope()
	{
		return HtmlActionScope.PREMIUM_HTML;
	}
}
