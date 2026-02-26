package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.clan.enums.ClanHallGrade;
import org.l2jmobius.gameserver.model.residences.AbstractResidence;
import org.l2jmobius.gameserver.model.residences.ResidenceFunction;
import org.l2jmobius.gameserver.model.residences.ResidenceFunctionType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class AgitDecoInfo extends ServerPacket
{
	private final AbstractResidence _residense;

	public AgitDecoInfo(AbstractResidence residense)
	{
		this._residense = residense;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.AGIT_DECO_INFO.writeId(this, buffer);
		buffer.writeInt(this._residense.getResidenceId());
		ResidenceFunction function = this._residense.getFunction(ResidenceFunctionType.HP_REGEN);
		if (function != null && function.getLevel() != 0)
		{
			if ((this._residense.getGrade() != ClanHallGrade.GRADE_NONE || function.getLevel() >= 2) && (this._residense.getGrade() != ClanHallGrade.GRADE_D || function.getLevel() >= 3) && (this._residense.getGrade() != ClanHallGrade.GRADE_C || function.getLevel() >= 4) && (this._residense.getGrade() != ClanHallGrade.GRADE_B || function.getLevel() >= 5))
			{
				buffer.writeByte(2);
			}
			else
			{
				buffer.writeByte(1);
			}
		}
		else
		{
			buffer.writeByte(0);
		}

		function = this._residense.getFunction(ResidenceFunctionType.MP_REGEN);
		if (function != null && function.getLevel() != 0)
		{
			if ((this._residense.getGrade() != ClanHallGrade.GRADE_NONE && this._residense.getGrade() != ClanHallGrade.GRADE_D || function.getLevel() >= 2) && (this._residense.getGrade() != ClanHallGrade.GRADE_C || function.getLevel() >= 3) && (this._residense.getGrade() != ClanHallGrade.GRADE_B || function.getLevel() >= 4))
			{
				buffer.writeByte(2);
				buffer.writeByte(2);
			}
			else
			{
				buffer.writeByte(1);
				buffer.writeByte(1);
			}
		}
		else
		{
			buffer.writeByte(0);
			buffer.writeByte(0);
		}

		function = this._residense.getFunction(ResidenceFunctionType.EXP_RESTORE);
		if (function != null && function.getLevel() != 0)
		{
			if (function.getLevel() < 2)
			{
				buffer.writeByte(1);
			}
			else
			{
				buffer.writeByte(2);
			}
		}
		else
		{
			buffer.writeByte(0);
		}

		function = this._residense.getFunction(ResidenceFunctionType.TELEPORT);
		if (function != null && function.getLevel() != 0)
		{
			if (function.getLevel() < 2)
			{
				buffer.writeByte(1);
			}
			else
			{
				buffer.writeByte(2);
			}
		}
		else
		{
			buffer.writeByte(0);
		}

		buffer.writeByte(0);
		function = this._residense.getFunction(ResidenceFunctionType.CURTAIN);
		if (function != null && function.getLevel() != 0)
		{
			if (function.getLevel() < 2)
			{
				buffer.writeByte(1);
			}
			else
			{
				buffer.writeByte(2);
			}
		}
		else
		{
			buffer.writeByte(0);
		}

		function = this._residense.getFunction(ResidenceFunctionType.ITEM);
		if (function != null && function.getLevel() != 0)
		{
			if ((this._residense.getGrade() != ClanHallGrade.GRADE_NONE || function.getLevel() >= 2) && function.getLevel() >= 3)
			{
				buffer.writeByte(2);
			}
			else
			{
				buffer.writeByte(1);
			}
		}
		else
		{
			buffer.writeByte(0);
		}

		function = this._residense.getFunction(ResidenceFunctionType.BUFF);
		if (function != null && function.getLevel() != 0)
		{
			if ((this._residense.getGrade() != ClanHallGrade.GRADE_NONE || function.getLevel() >= 2) && (this._residense.getGrade() != ClanHallGrade.GRADE_D || function.getLevel() >= 4) && (this._residense.getGrade() != ClanHallGrade.GRADE_C || function.getLevel() >= 5) && (this._residense.getGrade() != ClanHallGrade.GRADE_B || function.getLevel() >= 8))
			{
				buffer.writeByte(2);
			}
			else
			{
				buffer.writeByte(1);
			}
		}
		else
		{
			buffer.writeByte(0);
		}

		function = this._residense.getFunction(ResidenceFunctionType.OUTERFLAG);
		if (function != null && function.getLevel() != 0)
		{
			if (function.getLevel() < 2)
			{
				buffer.writeByte(1);
			}
			else
			{
				buffer.writeByte(2);
			}
		}
		else
		{
			buffer.writeByte(0);
		}

		function = this._residense.getFunction(ResidenceFunctionType.PLATFORM);
		if (function != null && function.getLevel() != 0)
		{
			if (function.getLevel() < 2)
			{
				buffer.writeByte(1);
			}
			else
			{
				buffer.writeByte(2);
			}
		}
		else
		{
			buffer.writeByte(0);
		}

		function = this._residense.getFunction(ResidenceFunctionType.ITEM);
		if (function != null && function.getLevel() != 0)
		{
			if ((this._residense.getGrade() != ClanHallGrade.GRADE_NONE || function.getLevel() >= 2) && function.getLevel() >= 3)
			{
				buffer.writeByte(2);
			}
			else
			{
				buffer.writeByte(1);
			}
		}
		else
		{
			buffer.writeByte(0);
		}
	}
}
