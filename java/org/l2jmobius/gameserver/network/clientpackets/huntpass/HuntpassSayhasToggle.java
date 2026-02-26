package org.l2jmobius.gameserver.network.clientpackets.huntpass;

import org.l2jmobius.gameserver.model.HuntPass;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.huntpass.HuntPassSayhasSupportInfo;

public class HuntpassSayhasToggle extends ClientPacket
{
	private boolean _sayhaToggle;

	@Override
	protected void readImpl()
	{
		this._sayhaToggle = this.readByte() != 0;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			HuntPass huntPass = player.getHuntPass();
			if (huntPass != null)
			{
				int timeEarned = huntPass.getAvailableSayhaTime();
				int timeUsed = huntPass.getUsedSayhaTime();
				if (player.getVitalityPoints() < 35000)
				{
					player.sendPacket(SystemMessageId.UNABLE_TO_ACTIVATE_YOU_CAN_USE_SAYHA_S_GRACE_SUSTENTION_EFFECT_OF_THE_SEASON_PASS_ONLY_IF_YOU_HAVE_AT_LEAST_35_000_SAYHA_S_GRACE_POINTS);
				}
				else
				{
					if (this._sayhaToggle && timeEarned > 0 && timeEarned > timeUsed)
					{
						huntPass.setSayhasSustention(true);
					}
					else
					{
						huntPass.setSayhasSustention(false);
					}

					player.sendPacket(new HuntPassSayhasSupportInfo(player));
				}
			}
		}
	}
}
