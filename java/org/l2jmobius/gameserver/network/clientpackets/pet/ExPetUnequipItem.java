package org.l2jmobius.gameserver.network.clientpackets.pet;

import org.l2jmobius.gameserver.ai.Action;
import org.l2jmobius.gameserver.ai.Intention;
import org.l2jmobius.gameserver.ai.NextAction;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Pet;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.zone.ZoneId;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.network.serverpackets.pet.ExPetSkillList;
import org.l2jmobius.gameserver.network.serverpackets.pet.PetSummonInfo;

public class ExPetUnequipItem extends ClientPacket
{
	private int _objectId;
	private int _itemId;

	@Override
	protected void readImpl()
	{
		this._objectId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Pet pet = player.getPet();
			if (pet != null)
			{
				if (this.getClient().getFloodProtectors().canUseItem())
				{
					if (player.isInsideZone(ZoneId.JAIL))
					{
						player.sendMessage("You cannot use items while jailed.");
					}
					else
					{
						if (player.getActiveTradeList() != null)
						{
							player.cancelActiveTrade();
						}

						if (player.isInStoreMode())
						{
							player.sendPacket(SystemMessageId.WHILE_OPERATING_A_PRIVATE_STORE_OR_WORKSHOP_YOU_CANNOT_DISCARD_DESTROY_OR_TRADE_AN_ITEM);
							player.sendPacket(ActionFailed.STATIC_PACKET);
						}
						else
						{
							Item item = pet.getInventory().getItemByObjectId(this._objectId);
							if (!player.hasBlockActions() && !player.isControlBlocked() && !player.isAlikeDead())
							{
								if (player.isDead() || pet.isDead() || !player.getInventory().canManipulateWithItemId(item.getId()))
								{
									SystemMessage sm = new SystemMessage(SystemMessageId.S1_CANNOT_BE_USED_THE_REQUIREMENTS_ARE_NOT_MET);
									sm.addItemName(item);
									player.sendPacket(sm);
								}
								else if (item.isEquipable())
								{
									this._itemId = item.getId();
									if (!player.isFishing() || this._itemId >= 6535 && this._itemId <= 6540)
									{
										player.onActionRequest();
										if (item.isEquipable())
										{
											if (pet.getInventory().isItemSlotBlocked(item.getTemplate().getBodyPart()))
											{
												player.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
												return;
											}

											switch (item.getTemplate().getBodyPart())
											{
												case LR_HAND:
												case L_HAND:
												case R_HAND:
													if (player.getActiveWeaponItem() != null && player.getActiveWeaponItem().getId() == 93331)
													{
														player.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
														return;
													}
													break;
												case DECO:
													if (!item.isEquipped() && player.getInventory().getTalismanSlots() == 0)
													{
														player.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
														return;
													}
													break;
												case BROOCH_JEWEL:
													if (!item.isEquipped() && player.getInventory().getBroochJewelSlots() == 0)
													{
														SystemMessage sm = new SystemMessage(SystemMessageId.YOU_CANNOT_EQUIP_S1_WITHOUT_EQUIPPING_A_BROOCH);
														sm.addItemName(item);
														player.sendPacket(sm);
														return;
													}
													break;
												case AGATHION:
													if (!item.isEquipped() && player.getInventory().getAgathionSlots() == 0)
													{
														player.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
														return;
													}
													break;
												case ARTIFACT:
													if (!item.isEquipped() && player.getInventory().getArtifactSlots() == 0)
													{
														SystemMessage sm = new SystemMessage(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
														sm.addItemName(item);
														player.sendPacket(sm);
														return;
													}
											}

											if (player.isCastingNow())
											{
												player.getAI().setNextAction(new NextAction(Action.FINISH_CASTING, Intention.CAST, () -> {
													pet.transferItem(ItemProcessType.TRANSFER, item.getObjectId(), 1L, player.getInventory(), player, null);
													this.sendInfos(pet, player);
												}));
											}
											else if (player.isAttackingNow())
											{
												pet.transferItem(ItemProcessType.TRANSFER, item.getObjectId(), 1L, player.getInventory(), player, null);
												this.sendInfos(pet, player);
											}
											else
											{
												pet.transferItem(ItemProcessType.TRANSFER, item.getObjectId(), 1L, player.getInventory(), player, null);
												this.sendInfos(pet, player);
											}
										}
									}
									else
									{
										player.sendPacket(SystemMessageId.YOU_CANNOT_DO_THAT_WHILE_FISHING_3);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	protected void sendInfos(Pet pet, Player player)
	{
		pet.getStat().recalculateStats(true);
		player.sendPacket(new PetSummonInfo(pet, 1));
		player.sendPacket(new ExPetSkillList(false, pet));
	}
}
