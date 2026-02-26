package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.RecipeData;
import org.l2jmobius.gameserver.model.RecipeList;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.stat.PlayerStat;
import org.l2jmobius.gameserver.model.stats.Stat;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.ServerPackets;

public class RecipeItemMakeInfo extends ServerPacket
{
	private final int _id;
	private final Player _player;
	private final boolean _success;
	private final double _craftRate;
	private final double _craftCritical;

	public RecipeItemMakeInfo(int id, Player player, boolean success)
	{
		this._id = id;
		this._player = player;
		this._success = success;
		PlayerStat stat = player.getStat();
		this._craftRate = stat.getValue(Stat.CRAFT_RATE, 0.0);
		this._craftCritical = stat.getValue(Stat.CRAFTING_CRITICAL, 0.0);
	}

	public RecipeItemMakeInfo(int id, Player player)
	{
		this._id = id;
		this._player = player;
		this._success = true;
		PlayerStat stat = player.getStat();
		this._craftRate = stat.getValue(Stat.CRAFT_RATE, 0.0);
		this._craftCritical = stat.getValue(Stat.CRAFTING_CRITICAL, 0.0);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		RecipeList recipe = RecipeData.getInstance().getRecipeList(this._id);
		if (recipe == null)
		{
			PacketLogger.info("Character: " + this._player + ": Requested unexisting recipe with id = " + this._id);
		}
		else
		{
			ServerPackets.RECIPE_ITEM_MAKE_INFO.writeId(this, buffer);
			buffer.writeInt(this._id);
			buffer.writeInt(!recipe.isDwarvenRecipe());
			buffer.writeInt((int) this._player.getCurrentMp());
			buffer.writeInt(this._player.getMaxMp());
			buffer.writeInt(this._success);
			buffer.writeByte(0);
			buffer.writeLong(0L);
			buffer.writeDouble(Math.min(this._craftRate, 100.0));
			buffer.writeByte(this._craftCritical > 0.0);
			buffer.writeDouble(Math.min(this._craftCritical, 100.0));
			buffer.writeByte(0);
		}
	}
}
