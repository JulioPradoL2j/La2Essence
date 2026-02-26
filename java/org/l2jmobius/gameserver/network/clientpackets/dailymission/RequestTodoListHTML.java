package org.l2jmobius.gameserver.network.clientpackets.dailymission;

import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestTodoListHTML extends ClientPacket
{
	protected int _tab;
	protected String _linkName;

	@Override
	protected void readImpl()
	{
		this._tab = this.readByte();
		this._linkName = this.readString();
	}

	@Override
	protected void runImpl()
	{
	}
}
