package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.data.xml.SkillData;
import org.l2jmobius.gameserver.model.actor.Playable;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.Summon;
import org.l2jmobius.gameserver.model.actor.transform.Transform;
import org.l2jmobius.gameserver.model.skill.CommonSkill;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;

public class RequestMagicSkillUse extends ClientPacket
{
	private int _magicId;
	private boolean _ctrlPressed;
	private boolean _shiftPressed;

	@Override
	protected void readImpl()
	{
		this._magicId = this.readInt();
		this._ctrlPressed = this.readInt() != 0;
		this._shiftPressed = this.readByte() != 0;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			this._magicId = player.getReplacementSkill(this._magicId);
			Skill skill = player.getKnownSkill(this._magicId);
			if (skill == null)
			{
				if (this._magicId != CommonSkill.HAIR_ACCESSORY_SET.getId() && (this._magicId <= 1565 || this._magicId >= 1570))
				{
					Playable pet = null;
					if (player.hasServitors())
					{
						for (Summon summon : player.getServitors().values())
						{
							skill = summon.getKnownSkill(this._magicId);
							if (skill != null)
							{
								pet = summon;
								break;
							}
						}
					}

					if (skill == null && player.hasPet())
					{
						pet = player.getPet();
						skill = pet.getKnownSkill(this._magicId);
					}

					if (skill != null && pet != null)
					{
						player.onActionRequest();
						pet.setTarget(null);
						pet.useMagic(skill, null, this._ctrlPressed, false);
						return;
					}
				}
				else
				{
					skill = SkillData.getInstance().getSkill(this._magicId, 1);
				}

				if (skill == null)
				{
					skill = player.getCustomSkill(this._magicId);
				}

				if (skill == null)
				{
					player.sendPacket(ActionFailed.STATIC_PACKET);
					return;
				}
			}

			if (skill.isBlockActionUseSkill())
			{
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else if (player.isInAirShip())
			{
				player.sendPacket(SystemMessageId.THIS_ACTION_IS_PROHIBITED_WHILE_MOUNTED_OR_ON_AN_AIRSHIP);
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
			else
			{
				Transform transform = player.getTransformation();
				if (transform != null && !transform.canUseWeaponStats() && !player.hasTransformSkill(skill))
				{
					player.sendPacket(ActionFailed.STATIC_PACKET);
				}
				else
				{
					player.onActionRequest();
					player.useMagic(skill, null, this._ctrlPressed, this._shiftPressed);
				}
			}
		}
	}
}
