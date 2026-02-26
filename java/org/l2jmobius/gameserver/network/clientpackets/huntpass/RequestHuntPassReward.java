package org.l2jmobius.gameserver.network.clientpackets.huntpass;

import org.l2jmobius.commons.threads.ThreadPool;
import org.l2jmobius.gameserver.config.custom.OfflinePlayConfig;
import org.l2jmobius.gameserver.config.custom.OfflineTradeConfig;
import org.l2jmobius.gameserver.data.xml.HuntPassData;
import org.l2jmobius.gameserver.data.xml.ItemData;
import org.l2jmobius.gameserver.model.HuntPass;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.RewardRequest;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.network.serverpackets.huntpass.HuntPassInfo;
import org.l2jmobius.gameserver.network.serverpackets.huntpass.HuntPassSayhasSupportInfo;
import org.l2jmobius.gameserver.network.serverpackets.huntpass.HuntPassSimpleInfo;

public class RequestHuntPassReward extends ClientPacket
{
	private int _huntPassType;

	@Override
	protected void readImpl()
	{
		this._huntPassType = this.readByte();
		this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (!OfflineTradeConfig.OFFLINE_DISCONNECT_SAME_ACCOUNT || !OfflinePlayConfig.OFFLINE_PLAY_DISCONNECT_SAME_ACCOUNT)
			{
				int sameAccountPlayers = 0;

				for (Player worldPlayer : World.getInstance().getPlayers())
				{
					if (worldPlayer.getAccountName().equals(player.getAccountName()))
					{
						sameAccountPlayers++;
					}
				}

				if (sameAccountPlayers > 1)
				{
					player.sendMessage("Hunting rewards are shared across the account and cannot be received if more than one character is online simultaneously.");
					return;
				}
			}

			if (!player.hasRequest(RewardRequest.class))
			{
				player.addRequest(new RewardRequest(player));
				HuntPass huntPass = player.getHuntPass();
				int rewardIndex = huntPass.getRewardStep();
				int premiumRewardIndex = huntPass.getPremiumRewardStep();
				if (rewardIndex >= HuntPassData.getInstance().getRewardsCount() && premiumRewardIndex >= HuntPassData.getInstance().getPremiumRewardsCount())
				{
					player.removeRequest(RewardRequest.class);
				}
				else
				{
					ItemHolder reward = null;
					if (!huntPass.isPremium())
					{
						if (rewardIndex < huntPass.getCurrentStep())
						{
							reward = HuntPassData.getInstance().getRewards().get(rewardIndex);
						}
					}
					else if (rewardIndex < HuntPassData.getInstance().getRewardsCount())
					{
						if (rewardIndex < huntPass.getCurrentStep())
						{
							reward = HuntPassData.getInstance().getRewards().get(rewardIndex);
						}
					}
					else if (premiumRewardIndex < HuntPassData.getInstance().getPremiumRewardsCount() && premiumRewardIndex < huntPass.getCurrentStep())
					{
						reward = HuntPassData.getInstance().getPremiumRewards().get(premiumRewardIndex);
					}

					if (reward == null)
					{
						player.removeRequest(RewardRequest.class);
					}
					else
					{
						ItemTemplate itemTemplate = ItemData.getInstance().getTemplate(reward.getId());
						long weight = itemTemplate.getWeight() * reward.getCount();
						long slots = itemTemplate.isStackable() ? 1L : reward.getCount();
						if (player.getInventory().validateWeight(weight) && player.getInventory().validateCapacity(slots))
						{
							this.normalReward(player);
							this.premiumReward(player);
							huntPass.setRewardStep(rewardIndex + 1);
							huntPass.setRewardAlert(false);
							huntPass.store();
							player.sendPacket(new HuntPassInfo(player, this._huntPassType));
							player.sendPacket(new HuntPassSayhasSupportInfo(player));
							player.sendPacket(new HuntPassSimpleInfo(player));
							ThreadPool.schedule(() -> player.removeRequest(RewardRequest.class), 300L);
						}
						else
						{
							player.sendPacket(SystemMessageId.YOUR_INVENTORY_S_WEIGHT_SLOT_LIMIT_HAS_BEEN_EXCEEDED_SO_YOU_CANNOT_RECEIVE_THE_REWARD_PLEASE_FREE_UP_SOME_SPACE_AND_TRY_AGAIN);
							player.removeRequest(RewardRequest.class);
						}
					}
				}
			}
		}
	}

	protected void rewardItem(Player player, ItemHolder reward)
	{
		if (reward.getId() == 72286)
		{
			int count = (int) reward.getCount();
			player.getHuntPass().addSayhaTime(count);
			SystemMessage msg = new SystemMessage(SystemMessageId.YOU_VE_GOT_S1_SAYHA_S_GRACE_SUSTENTION_POINT_S);
			msg.addInt(count);
			player.sendPacket(msg);
		}
		else
		{
			player.addItem(ItemProcessType.REWARD, reward, player, true);
		}
	}

	private void premiumReward(Player player)
	{
		HuntPass huntPass = player.getHuntPass();
		int premiumRewardIndex = huntPass.getPremiumRewardStep();
		if (premiumRewardIndex < HuntPassData.getInstance().getPremiumRewardsCount())
		{
			if (huntPass.isPremium())
			{
				this.rewardItem(player, HuntPassData.getInstance().getPremiumRewards().get(premiumRewardIndex));
				huntPass.setPremiumRewardStep(premiumRewardIndex + 1);
			}
		}
	}

	private void normalReward(Player player)
	{
		HuntPass huntPass = player.getHuntPass();
		int rewardIndex = huntPass.getRewardStep();
		if (rewardIndex < HuntPassData.getInstance().getRewardsCount())
		{
			if (!huntPass.isPremium() || huntPass.getPremiumRewardStep() >= rewardIndex && huntPass.getPremiumRewardStep() < HuntPassData.getInstance().getPremiumRewardsCount())
			{
				this.rewardItem(player, HuntPassData.getInstance().getRewards().get(rewardIndex));
			}
		}
	}
}
