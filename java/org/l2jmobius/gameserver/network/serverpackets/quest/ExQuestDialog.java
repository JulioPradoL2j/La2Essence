package org.l2jmobius.gameserver.network.serverpackets.quest;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.script.QuestDialogType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExQuestDialog extends ServerPacket
{
	private final int _questId;
	private final QuestDialogType _dialogType;

	public ExQuestDialog(int questId, QuestDialogType dialogType)
	{
		this._questId = questId;
		this._dialogType = dialogType;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_QUEST_DIALOG.writeId(this, buffer);
		buffer.writeInt(this._questId);
		buffer.writeInt(this._dialogType.getId());
	}
}
