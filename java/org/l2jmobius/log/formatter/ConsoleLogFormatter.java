package org.l2jmobius.log.formatter;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import org.l2jmobius.commons.util.TraceUtil;

public class ConsoleLogFormatter extends Formatter
{
	@Override
	public String format(LogRecord record)
	{
		StringBuilder output = new StringBuilder(128);
		
		output.append(record.getLevel().getName()).append(": ").append(record.getMessage()).append(System.lineSeparator());
		
		if (record.getThrown() != null)
		{
			try
			{
				output.append(TraceUtil.getStackTrace(record.getThrown())).append(System.lineSeparator());
			}
			catch (Exception ignored)
			{
			}
		}
		
		return output.toString();
	}
}