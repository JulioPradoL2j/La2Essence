package org.l2jmobius.gameserver.network.clientpackets.captcha;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.CaptchaRequest;
import org.l2jmobius.gameserver.model.captcha.Captcha;
import org.l2jmobius.gameserver.model.captcha.CaptchaGenerator;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.captcha.ReceiveBotCaptchaImage;

public class RequestRefreshCaptcha extends ClientPacket
{
	private long _captchaId;

	@Override
	protected void readImpl()
	{
		this._captchaId = this.readLong();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			CaptchaRequest request = player.getRequest(CaptchaRequest.class);
			Captcha captcha = CaptchaGenerator.getInstance().next((int) this._captchaId);
			if (request != null)
			{
				request.refresh(captcha);
			}
			else
			{
				request = new CaptchaRequest(player, captcha);
				player.addRequest(request);
			}

			player.sendPacket(new ReceiveBotCaptchaImage(captcha, request.getRemainingTime()));
		}
	}
}
