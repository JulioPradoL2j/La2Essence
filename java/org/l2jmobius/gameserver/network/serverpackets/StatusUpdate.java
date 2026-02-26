package org.l2jmobius.gameserver.network.serverpackets;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.enums.StatusUpdateType;

public class StatusUpdate extends ServerPacket
{
	private final int _objectId;
	private int _casterObjectId = 0;
	private final boolean _isPlayable;
	private boolean _isVisible = false;
	private final Map<StatusUpdateType, Number> _updates = new LinkedHashMap<>();

	public StatusUpdate(WorldObject object)
	{
		this._objectId = object.getObjectId();
		this._isPlayable = object.isPlayable();
	}

	public void addUpdate(StatusUpdateType type, long level)
	{
		this._updates.put(type, level);
		if (this._isPlayable)
		{
			switch (type)
			{
				case CUR_HP:
				case CUR_MP:
				case CUR_CP:
				case CUR_DP:
				case CUR_BP:
					this._isVisible = true;
			}
		}
	}

	public void addCaster(WorldObject object)
	{
		this._casterObjectId = object.getObjectId();
	}

	public boolean hasUpdates()
	{
		return !this._updates.isEmpty();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.STATUS_UPDATE.writeId(this, buffer);
		buffer.writeInt(this._objectId);
		buffer.writeInt(this._isVisible ? this._casterObjectId : 0);
		buffer.writeByte(this._isVisible);
		buffer.writeByte(this._updates.size());

		for (Entry<StatusUpdateType, Number> entry : this._updates.entrySet())
		{
			int statusTypeId = entry.getKey().getClientId();
			buffer.writeByte(statusTypeId);
			if (statusTypeId != StatusUpdateType.CUR_HP.getClientId() && statusTypeId != StatusUpdateType.MAX_HP.getClientId())
			{
				buffer.writeInt(entry.getValue().intValue());
			}
			else
			{
				buffer.writeLong(entry.getValue().longValue());
			}
		}
	}

	@Override
	public boolean canBeDropped(GameClient client)
	{
		return true;
	}
}
