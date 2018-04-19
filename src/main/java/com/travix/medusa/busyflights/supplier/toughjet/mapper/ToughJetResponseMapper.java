package com.travix.medusa.busyflights.supplier.toughjet.mapper;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.supplier.toughjet.ToughJetConstanst;
import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetResponse;
import com.travix.medusa.busyflights.utils.FormatUtils;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import java.time.ZoneId;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;


public class ToughJetResponseMapper extends PropertyMap<ToughJetResponse, BusyFlightsResponse>
{
	@Override
	protected void configure()
	{
		Converter<String, String> dateConverter = FormatUtils.getDateConverter(ISO_INSTANT.withZone(ZoneId.systemDefault()), ISO_LOCAL_DATE);
		using(dateConverter).map().setDepartureDate(source.getOutboundDateTime());
		using(dateConverter).map().setArrivalDate(source.getInboundDateTime());
		Converter<ToughJetResponse, Double> fareConverter =ctx -> calculateFare(ctx.getSource());
		using(fareConverter).map(source, destination.getFare());
		map().setAirline(source.getCarrier());
		map().setDepartureAirportCode(source.getDepartureAirportName());
		map().setDestinationAirportCode(source.getArrivalAirportName());
		map().setSupplier(ToughJetConstanst.NAME);
	}

	private Double calculateFare(ToughJetResponse source)
	{
		double totalPrice = source.getBasePrice();
		totalPrice = totalPrice*(1-source.getDiscount());
		totalPrice += source.getTax();
		return FormatUtils.formatDouble(totalPrice);
	}

}
