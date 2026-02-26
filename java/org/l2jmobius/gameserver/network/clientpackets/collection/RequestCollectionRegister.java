package org.l2jmobius.gameserver.network.clientpackets.collection;

import org.l2jmobius.gameserver.data.holders.CollectionDataHolder;
import org.l2jmobius.gameserver.data.xml.CollectionData;
import org.l2jmobius.gameserver.data.xml.OptionData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.PlayerCollectionData;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.holders.ItemEnchantHolder;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.options.Options;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ConfirmDlg;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.network.serverpackets.collection.ExCollectionComplete;
import org.l2jmobius.gameserver.network.serverpackets.collection.ExCollectionRegister;

public class RequestCollectionRegister extends ClientPacket
{
	private int _collectionId;
	private int _index;
	private int _itemObjId;

	@Override
	protected void readImpl()
	{
		this._collectionId = this.readShort();
		this._index = this.readInt();
		this._itemObjId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Item item = player.getInventory().getItemByObjectId(this._itemObjId);
			if (item == null)
			{
				player.sendMessage("Item not found.");
			}
			else
			{
				CollectionDataHolder collection = CollectionData.getInstance().getCollection(this._collectionId);
				if (collection == null)
				{
					player.sendMessage("Could not find collection.");
				}
				else
				{
					long count = 0L;

					for (ItemEnchantHolder data : collection.getItems())
					{
						if (data.getId() == item.getId() && (data.getEnchantLevel() == 0 || data.getEnchantLevel() == item.getEnchantLevel()))
						{
							count = data.getCount();
							break;
						}
					}

					if (count != 0L && item.getCount() >= count && !item.isEquipped())
					{
						PlayerCollectionData currentColl = null;

						for (PlayerCollectionData coll : player.getCollections())
						{
							if (coll.getCollectionId() == this._collectionId)
							{
								currentColl = coll;
								break;
							}
						}

						if (currentColl != null && currentColl.getIndex() == this._index)
						{
							player.sendPacket(new ExCollectionRegister(false, this._collectionId, this._index, new ItemEnchantHolder(item.getId(), count, item.getEnchantLevel())));
							player.sendPacket(SystemMessageId.THIS_ITEM_CANNOT_BE_ADDED_TO_YOUR_COLLECTION);
							player.sendPacket(new ConfirmDlg("Collection already registered;"));
						}
						else
						{
							player.destroyItem(ItemProcessType.FEE, item, count, player, true);
							player.sendPacket(new ExCollectionRegister(true, this._collectionId, this._index, new ItemEnchantHolder(item.getId(), count, item.getEnchantLevel())));
							player.getCollections().add(new PlayerCollectionData(this._collectionId, item.getId(), this._index));
							int completeCount = 0;

							for (PlayerCollectionData collx : player.getCollections())
							{
								if (collx.getCollectionId() == this._collectionId)
								{
									completeCount++;
								}
							}

							if (completeCount == collection.getCompleteCount())
							{
								player.sendPacket(new ExCollectionComplete(this._collectionId));
								player.sendPacket(new SystemMessage(SystemMessageId.S1_COLLECTION_IS_COMPLETE).addString(""));
								Options options = OptionData.getInstance().getOptions(collection.getOptionId());
								if (options != null)
								{
									options.apply(player);
								}
							}
						}
					}
					else
					{
						player.sendMessage("Incorrect item count.");
					}
				}
			}
		}
	}
}
