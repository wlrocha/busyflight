package com.travix.medusa.busyflights.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateFormatValidator implements ConstraintValidator<DateFormat, String>
{
	private String pattern;

	public void initialize(DateFormat dateFormat)
	{
		pattern = dateFormat.pattern();
	}

	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		if(value == null)
		{
			return true;
		}
		try
		{
			LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
			return true;
		}catch (Exception e)
		{
			return false;
		}
	}
}
