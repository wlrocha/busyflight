package com.travix.medusa.busyflights.supplier.crazyair.mapper;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.supplier.crazyair.CrazyAirConstants;
import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirResponse;
import com.travix.medusa.busyflights.utils.FormatUtils;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;


public class CrazyAirResponseMapper extends PropertyMap<CrazyAirResponse, BusyFlightsResponse>
{
	@Override
	protected void configure()
	{
		Converter<String, String> dateConverter = FormatUtils.getDateConverter(ISO_LOCAL_DATE_TIME, ISO_LOCAL_DATE);
		using(dateConverter).map().setDepartureDate(source.getDepartureDate());
		using(dateConverter).map().setArrivalDate(source.getArrivalDate());
		Converter<Double, Double> priceConverter = ctx -> FormatUtils.formatDouble(ctx.getSource());
		using(priceConverter).map().setFare(source.getPrice());
		map().setSupplier(CrazyAirConstants.NAME);
	}

}
