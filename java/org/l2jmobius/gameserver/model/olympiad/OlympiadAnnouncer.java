package org.l2jmobius.gameserver.model.olympiad;

import org.l2jmobius.gameserver.data.SpawnTable;
import org.l2jmobius.gameserver.model.Spawn;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.network.NpcStringId;
import org.l2jmobius.gameserver.network.enums.ChatType;

public class OlympiadAnnouncer implements Runnable
{
	private int _currentStadium = 0;

	@Override
	public void run()
	{
		int i = OlympiadGameManager.getInstance().getNumberOfStadiums();

		while (--i >= 0)
		{
			if (this._currentStadium >= OlympiadGameManager.getInstance().getNumberOfStadiums())
			{
				this._currentStadium = 0;
			}

			OlympiadGameTask task = OlympiadGameManager.getInstance().getOlympiadTask(this._currentStadium);
			label42:
			if (task != null && task.getGame() != null && task.needAnnounce())
			{
				String arenaId = String.valueOf(task.getGame().getStadiumId() + 1);
				NpcStringId npcString;
				switch (task.getGame().getType())
				{
					case NON_CLASSED:
						npcString = NpcStringId.THE_DUELS_BETWEEN_PLAYERS_OF_ANY_CLASS_WILL_START_SHORTLY_IN_ARENA_S1;
						break;
					case CLASSED:
						npcString = NpcStringId.THE_CLASS_DUELS_WILL_START_SHORTLY_IN_ARENA_S1;
						break;
					default:
						break label42;
				}

				for (Spawn spawn : SpawnTable.getInstance().getSpawns(31688))
				{
					Npc manager = spawn.getLastSpawn();
					if (manager != null)
					{
						manager.broadcastSay(ChatType.NPC_SHOUT, npcString, arenaId);
					}
				}
				break;
			}

			this._currentStadium++;
		}
	}
}
