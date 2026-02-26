package org.l2jmobius.gameserver.network.clientpackets.primeshop;

import java.util.Calendar;

import org.l2jmobius.commons.threads.ThreadPool;
import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.config.VipSystemConfig;
import org.l2jmobius.gameserver.data.sql.CharInfoTable;
import org.l2jmobius.gameserver.data.xml.PrimeShopData;
import org.l2jmobius.gameserver.managers.MailManager;
import org.l2jmobius.gameserver.managers.PunishmentManager;
import org.l2jmobius.gameserver.model.Message;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.PrimeShopRequest;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.itemcontainer.Mail;
import org.l2jmobius.gameserver.model.primeshop.PrimeShopGroup;
import org.l2jmobius.gameserver.model.primeshop.PrimeShopItem;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.enums.ExBrProductReplyType;
import org.l2jmobius.gameserver.network.enums.MailType;
import org.l2jmobius.gameserver.network.serverpackets.primeshop.ExBRBuyProduct;
import org.l2jmobius.gameserver.network.serverpackets.primeshop.ExBRGamePoint;

public class RequestBRPresentBuyProduct extends ClientPacket
{
	 
	private int _brId;
	private int _count;
	private String _charName;
	private String _mailTitle;
	private String _mailBody;

	@Override
	protected void readImpl()
	{
		this._brId = this.readInt();
		this._count = this.readInt();
		this._charName = this.readString();
		this._mailTitle = this.readString();
		this._mailBody = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			int receiverId = CharInfoTable.getInstance().getIdByName(this._charName);
			if (receiverId <= 0)
			{
				player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.INVALID_USER));
			}
			else if (!player.hasItemRequest() && !player.hasRequest(PrimeShopRequest.class))
			{
				player.addRequest(new PrimeShopRequest(player));
				PrimeShopGroup item = PrimeShopData.getInstance().getItem(this._brId);
				if (item.isVipGift())
				{
					player.sendMessage("You cannot gift a Vip Gift!");
				}
				else
				{
					if (validatePlayer(item, this._count, player))
					{
						int price = item.getPrice() * this._count;
						if (price < 1)
						{
							player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.LACK_OF_POINT));
							player.removeRequest(PrimeShopRequest.class);
							return;
						}

						int paymentId = validatePaymentId(item, price);
						if (paymentId < 0)
						{
							player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.LACK_OF_POINT));
							player.removeRequest(PrimeShopRequest.class);
							return;
						}

						if (paymentId > 0)
						{
							if (!player.destroyItemByItemId(ItemProcessType.FEE, paymentId, price, player, true))
							{
								player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.LACK_OF_POINT));
								player.removeRequest(PrimeShopRequest.class);
								return;
							}
						}
						else if (paymentId == 0)
						{
							if (player.getPrimePoints() < price)
							{
								player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.LACK_OF_POINT));
								player.removeRequest(PrimeShopRequest.class);
								return;
							}

							player.setPrimePoints(player.getPrimePoints() - price);
							if (VipSystemConfig.VIP_SYSTEM_PRIME_AFFECT)
							{
								player.updateVipPoints(price);
							}
						}

						player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.SUCCESS));
						player.sendPacket(new ExBRGamePoint(player));
						Message mail = new Message(receiverId, this._mailTitle, this._mailBody, MailType.PRIME_SHOP_GIFT);
						Mail attachement = mail.createAttachments();

						for (PrimeShopItem subItem : item.getItems())
						{
							attachement.addItem(ItemProcessType.REWARD, subItem.getId(), subItem.getCount() * this._count, player, this);
						}

						MailManager.getInstance().sendMessage(mail);
					}

					ThreadPool.schedule(() -> player.removeRequest(PrimeShopRequest.class), 1000L);
				}
			}
			else
			{
				player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.INVALID_USER_STATE));
			}
		}
	}

	private static boolean validatePlayer(PrimeShopGroup item, int count, Player player)
	{
		long currentTime = System.currentTimeMillis() / 1000L;
		if (item == null)
		{
			player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.INVALID_PRODUCT));
			PunishmentManager.handleIllegalPlayerAction(player, player + " tried to buy invalid brId from Prime", GeneralConfig.DEFAULT_PUNISH);
			return false;
		}
		else if (count >= 1 && count <= 99)
		{
			if (item.getMinLevel() > 0 && item.getMinLevel() > player.getLevel())
			{
				player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.INVALID_USER));
				return false;
			}
			else if (item.getMaxLevel() > 0 && item.getMaxLevel() < player.getLevel())
			{
				player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.INVALID_USER));
				return false;
			}
			else if (item.getMinBirthday() > 0 && item.getMinBirthday() > player.getBirthdays())
			{
				player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.INVALID_USER_STATE));
				return false;
			}
			else if (item.getMaxBirthday() > 0 && item.getMaxBirthday() < player.getBirthdays())
			{
				player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.INVALID_USER_STATE));
				return false;
			}
			else if ((Calendar.getInstance().get(7) & item.getDaysOfWeek()) == 0)
			{
				player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.NOT_DAY_OF_WEEK));
				return false;
			}
			else if (item.getStartSale() > 1 && item.getStartSale() > currentTime)
			{
				player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.BEFORE_SALE_DATE));
				return false;
			}
			else if (item.getEndSale() > 1 && item.getEndSale() < currentTime)
			{
				player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.AFTER_SALE_DATE));
				return false;
			}
			else
			{
				int weight = item.getWeight() * count;
				long slots = item.getCount() * count;
				if (player.getInventory().validateWeight(weight))
				{
					if (!player.getInventory().validateCapacity(slots))
					{
						player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.INVENTORY_OVERFLOW));
						return false;
					}
					return true;
				}
				player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.INVENTORY_OVERFLOW));
				return false;
			}
		}
		else
		{
			PunishmentManager.handleIllegalPlayerAction(player, player + " tried to buy invalid itemcount [" + count + "] from Prime", GeneralConfig.DEFAULT_PUNISH);
			player.sendPacket(new ExBRBuyProduct(ExBrProductReplyType.INVALID_USER_STATE));
			return false;
		}
	}

	private static int validatePaymentId(PrimeShopGroup item, long amount)
	{
		switch (item.getPaymentType())
		{
			case 0:
				return 0;
			case 1:
				return 57;
			case 2:
				return 23805;
			default:
				return -1;
		}
	}
}
