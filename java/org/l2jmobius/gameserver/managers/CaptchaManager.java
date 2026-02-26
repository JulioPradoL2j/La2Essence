package org.l2jmobius.gameserver.managers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.config.custom.CaptchaConfig;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Monster;
import org.l2jmobius.gameserver.model.actor.request.CaptchaRequest;
import org.l2jmobius.gameserver.model.captcha.Captcha;
import org.l2jmobius.gameserver.model.captcha.CaptchaGenerator;
import org.l2jmobius.gameserver.model.events.Containers;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.actor.creature.OnCreatureKilled;
import org.l2jmobius.gameserver.model.events.listeners.ConsumerEventListener;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.captcha.ReceiveBotCaptchaImage;

public class CaptchaManager
{
	private static final Logger LOGGER = Logger.getLogger(CaptchaManager.class.getName());
	private static final Map<Integer, Integer> MONSTER_COUNTER = new ConcurrentHashMap<>();
	private static final Map<Integer, Long> LAST_KILL_TIME = new ConcurrentHashMap<>();

	protected CaptchaManager()
	{
		if (CaptchaConfig.ENABLE_CAPTCHA)
		{
			Containers.Players().addListener(new ConsumerEventListener(Containers.Players(), EventType.ON_CREATURE_KILLED, event -> this.updateCounter(((OnCreatureKilled) event).getAttacker(), ((OnCreatureKilled) event).getTarget()), this));
			LOGGER.info(this.getClass().getSimpleName() + ": Enabled.");
		}
	}

	public void updateCounter(Creature player, Creature monster)
	{
		if (CaptchaConfig.ENABLE_CAPTCHA)
		{
			if (player instanceof Player && monster instanceof Monster)
			{
				Player killer = player.asPlayer();
				if (!GeneralConfig.ENABLE_AUTO_PLAY || !killer.isAutoPlaying())
				{
					if (CaptchaConfig.KILL_COUNTER_RESET)
					{
						long currentTime = System.currentTimeMillis();
						long previousKillTime = LAST_KILL_TIME.getOrDefault(killer.getObjectId(), currentTime);
						if (currentTime - previousKillTime > CaptchaConfig.KILL_COUNTER_RESET_TIME)
						{
							MONSTER_COUNTER.put(killer.getObjectId(), 0);
						}

						LAST_KILL_TIME.put(killer.getObjectId(), currentTime);
					}

					int count = 1;
					if (MONSTER_COUNTER.get(killer.getObjectId()) != null)
					{
						count = MONSTER_COUNTER.get(killer.getObjectId()) + 1;
					}

					int next = Rnd.get(CaptchaConfig.KILL_COUNTER_RANDOMIZATION);
					if (CaptchaConfig.KILL_COUNTER + next < count)
					{
						MONSTER_COUNTER.remove(killer.getObjectId());
						Captcha captcha = CaptchaGenerator.getInstance().next();
						if (!killer.hasRequest(CaptchaRequest.class))
						{
							CaptchaRequest request = new CaptchaRequest(killer, captcha);
							killer.addRequest(request);
							killer.sendPacket(new ReceiveBotCaptchaImage(captcha, request.getRemainingTime()));
							killer.sendPacket(SystemMessageId.PLEASE_ENTER_THE_AUTHENTICATION_CODE_IN_TIME_TO_CONTINUE_PLAYING);
						}
					}
					else
					{
						MONSTER_COUNTER.put(killer.getObjectId(), count);
					}
				}
			}
		}
	}

	public static final CaptchaManager getInstance()
	{
		return CaptchaManager.SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final CaptchaManager INSTANCE = new CaptchaManager();
	}
}
