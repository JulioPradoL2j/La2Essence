package org.l2jmobius.gameserver.network.clientpackets.adenlab;

import java.util.logging.Logger;

import org.l2jmobius.gameserver.config.AdenLaboratoryConfig;
import org.l2jmobius.gameserver.managers.AdenLaboratoryManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.AdenLabRequest;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;

public class RequestAdenLabBossInfo extends ClientPacket
{
	private static final Logger LOGGER = Logger.getLogger(RequestAdenLabBossInfo.class.getName());
	private int _bossId;

	@Override
	protected void readImpl()
	{
		this._bossId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player activeChar = this.getPlayer();
		if (activeChar != null)
		{
			if (AdenLaboratoryConfig.ADENLAB_ENABLED && !activeChar.hasRequest(AdenLabRequest.class))
			{
				AdenLaboratoryManager.processRequestAdenLabBossInfo(activeChar, (byte) this._bossId);
			}
			else
			{
				LOGGER.warning("Player " + activeChar.getName() + " [" + activeChar.getObjectId() + "] tried to access the Aden Lab while it is disabled or has another active request.");
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
				activeChar.sendPacket(SystemMessageId.THE_FUNCTION_IS_UNAVAILABLE);
			}
		}
	}
}
