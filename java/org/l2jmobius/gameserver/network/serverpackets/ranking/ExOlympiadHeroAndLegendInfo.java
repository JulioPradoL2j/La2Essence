package org.l2jmobius.gameserver.network.serverpackets.ranking;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.RankManager;
import org.l2jmobius.gameserver.model.olympiad.Hero;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExOlympiadHeroAndLegendInfo extends ServerPacket
{
	private final Collection<RankManager.HeroInfo> _heroes = RankManager.getInstance().getSnapshotHeroList();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_OLYMPIAD_HERO_AND_LEGEND_INFO.writeId(this, buffer);
		if (!this._heroes.isEmpty())
		{
			boolean wroteCount = false;

			for (RankManager.HeroInfo hero : this._heroes)
			{
				if (hero.isTopHero)
				{
					buffer.writeByte(1);
					buffer.writeByte(1);
				}
				else if (!wroteCount)
				{
					wroteCount = true;
					buffer.writeInt(Hero.getInstance().getHeroes().size() - 1);
				}

				buffer.writeSizedString(hero.charName);
				buffer.writeSizedString(hero.clanName);
				buffer.writeInt(hero.serverId);
				buffer.writeInt(hero.race);
				buffer.writeInt(hero.isMale ? 1 : 0);
				buffer.writeInt(hero.baseClass);
				buffer.writeInt(hero.level);
				buffer.writeInt(hero.legendCount);
				buffer.writeInt(hero.competitionsWon);
				buffer.writeInt(hero.competitionsLost);
				buffer.writeInt(hero.competitionsDrawn);
				buffer.writeInt(hero.olympiadPoints);
				buffer.writeInt(hero.clanLevel);
				if (!hero.isTopHero)
				{
					buffer.writeInt(this._heroes.size() - 1);
				}
			}
		}
	}
}
