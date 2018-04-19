package com.travix.medusa.busyflights.supplier.toughjet.mapper;



import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetRequest;
import org.modelmapper.PropertyMap;


public class ToughJetRequestMapper extends PropertyMap<BusyFlightsRequest, ToughJetRequest>
{
	@Override
	protected void configure()
	{
		map().setFrom(source.getOrigin());
		map().setTo(source.getDestination());
		map().setOutboundDate(source.getDepartureDate());
		map().setInboundDate(source.getReturnDate());
		map().setNumberOfAdults(source.getNumberOfPassengers());
	}
}
