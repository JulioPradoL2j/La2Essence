package org.l2jmobius.gameserver.model.skill.holders;

import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.skill.Skill;

public class SkillUseHolder extends SkillHolder
{
	private final Item _item;
	private final boolean _ctrlPressed;
	private final boolean _shiftPressed;

	public SkillUseHolder(Skill skill, Item item, boolean ctrlPressed, boolean shiftPressed)
	{
		super(skill);
		this._item = item;
		this._ctrlPressed = ctrlPressed;
		this._shiftPressed = shiftPressed;
	}

	public Item getItem()
	{
		return this._item;
	}

	public boolean isCtrlPressed()
	{
		return this._ctrlPressed;
	}

	public boolean isShiftPressed()
	{
		return this._shiftPressed;
	}
}
