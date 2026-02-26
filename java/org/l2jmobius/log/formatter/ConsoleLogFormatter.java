package org.l2jmobius.log.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import org.l2jmobius.commons.util.StringUtil;
import org.l2jmobius.commons.util.TraceUtil;

public class ConsoleLogFormatter extends Formatter
{
	private final SimpleDateFormat _dateFormat = new SimpleDateFormat("dd/MM HH:mm:ss");

	@Override
	public String format(LogRecord record)
	{
		StringBuilder output = new StringBuilder(128);
		StringUtil.append(output, "[", this._dateFormat.format(new Date(record.getMillis())), "] " + record.getMessage(), System.lineSeparator());
		if (record.getThrown() != null)
		{
			try
			{
				StringUtil.append(output, TraceUtil.getStackTrace(record.getThrown()), System.lineSeparator());
			}
			catch (Exception var4)
			{
			}
		}

		return output.toString();
	}
}
