package org.l2jmobius.log.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import org.l2jmobius.commons.util.StringUtil;

public class FileLogFormatter extends Formatter
{
	 
	private final SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss,SSS");

	@Override
	public String format(LogRecord record)
	{
		return StringUtil.concat(this._dateFormat.format(new Date(record.getMillis())), "\t", record.getLevel().getName(), "\t", String.valueOf(record.getLongThreadID()), "\t", record.getLoggerName(), "\t", record.getMessage(), System.lineSeparator());
	}
}
