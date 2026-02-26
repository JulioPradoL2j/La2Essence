package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class TutorialCloseHtml extends ServerPacket
{
	public static final TutorialCloseHtml STATIC_PACKET = new TutorialCloseHtml();

	private TutorialCloseHtml()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.TUTORIAL_CLOSE_HTML.writeId(this, buffer);
	}
}
