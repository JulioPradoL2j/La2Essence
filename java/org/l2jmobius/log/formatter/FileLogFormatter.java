package org.l2jmobius.log.formatter;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FileLogFormatter extends Formatter
{
	@Override
	public String format(LogRecord record)
	{
		return record.getLevel().getName() + ": " + record.getMessage() + System.lineSeparator();
	}
}