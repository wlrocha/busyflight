package com.travix.medusa.busyflights.utils;

import org.modelmapper.Converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

public class FormatUtils
{
	public static Double formatDouble(Double input)
	{
		return formatDouble(input, 2);
	}

	public static Double formatDouble(Double input, int decimals)
	{
		if(input == null)
		{
			return null;
		}
		return new BigDecimal(input).setScale(decimals, RoundingMode.HALF_UP).doubleValue();
	}

	public static Converter<String, String> getDateConverter(DateTimeFormatter sourceFormat, DateTimeFormatter targetFormat){
		return ctx -> formatDate(ctx.getSource(), sourceFormat, targetFormat);
	}

	public static String formatDate(String date, DateTimeFormatter source, DateTimeFormatter target)
	{
		if(date == null)
		{
			return null;
		}
		return target.format(source.parse(date));
	}
}
