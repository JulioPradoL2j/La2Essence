package org.l2jmobius.gameserver.network.clientpackets.mentoring;

import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.network.serverpackets.mentoring.ExMentorAdd;

public class RequestMenteeAdd extends ClientPacket
{
	private String _target;

	@Override
	protected void readImpl()
	{
		this._target = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player mentor = this.getPlayer();
		if (mentor != null)
		{
			Player mentee = World.getInstance().getPlayer(this._target);
			if (mentee != null)
			{
				if (ConfirmMenteeAdd.validate(mentor, mentee))
				{
					mentor.sendPacket(new SystemMessage(SystemMessageId.YOU_HAVE_OFFERED_TO_BECOME_S1_S_MENTOR).addString(mentee.getName()));
					mentee.sendPacket(new ExMentorAdd(mentor));
				}
			}
		}
	}
}
