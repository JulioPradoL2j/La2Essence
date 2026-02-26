package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPutIntensiveResultForVariationMake extends ServerPacket
{
	private final int _refinerItemObjId;
	private final int _lifestoneItemId;
	private final int _insertResult;

	public ExPutIntensiveResultForVariationMake(int lifeStoneId)
	{
		this._lifestoneItemId = lifeStoneId;
		this._refinerItemObjId = 0;
		this._insertResult = 0;
	}

	public ExPutIntensiveResultForVariationMake(int lifeStoneId, int refinerItemObjId, int insertResult)
	{
		this._refinerItemObjId = refinerItemObjId;
		this._lifestoneItemId = lifeStoneId;
		this._insertResult = insertResult;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PUT_INTENSIVE_RESULT_FOR_VARIATION_MAKE.writeId(this, buffer);
		buffer.writeInt(this._lifestoneItemId);
		buffer.writeInt(this._refinerItemObjId);
		buffer.writeByte(this._insertResult);
	}
}
