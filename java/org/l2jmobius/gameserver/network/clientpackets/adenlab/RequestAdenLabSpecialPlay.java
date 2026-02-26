package org.l2jmobius.gameserver.network.clientpackets.adenlab;

import org.l2jmobius.gameserver.config.AdenLaboratoryConfig;
import org.l2jmobius.gameserver.managers.AdenLaboratoryManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.AdenLabRequest;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;

public class RequestAdenLabSpecialPlay extends ClientPacket
{
	private int _bossId;
	private int _pageIndex;
	private int _feeIndex;

	@Override
	protected void readImpl()
	{
		this._bossId = this.readInt();
		this._pageIndex = this.readInt();
		this._feeIndex = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (AdenLaboratoryConfig.ADENLAB_ENABLED && !player.hasRequest(AdenLabRequest.class))
			{
				AdenLaboratoryManager.processRequestAdenLabSpecialPlay(player, (byte) this._bossId, (byte) this._pageIndex, this._feeIndex);
			}
			else
			{
				player.sendPacket(SystemMessageId.NOT_WORKING_PLEASE_TRY_AGAIN_LATER);
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
	}
}
