package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Map;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.ServerConfig;
import org.l2jmobius.gameserver.model.StatSet;
import org.l2jmobius.gameserver.model.olympiad.Hero;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExHeroList extends ServerPacket
{
	private final Map<Integer, StatSet> _heroList = Hero.getInstance().getHeroes();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_HERO_LIST.writeId(this, buffer);
		buffer.writeInt(this._heroList.size());

		for (StatSet hero : this._heroList.values())
		{
			buffer.writeString(hero.getString("char_name"));
			buffer.writeInt(hero.getInt("class_id"));
			buffer.writeString(hero.getString("clan_name", ""));
			buffer.writeInt(0);
			buffer.writeString(hero.getString("ally_name", ""));
			buffer.writeInt(0);
			buffer.writeInt(hero.getInt("count"));
			buffer.writeInt(ServerConfig.SERVER_ID);
			buffer.writeByte(0);
		}
	}
}
