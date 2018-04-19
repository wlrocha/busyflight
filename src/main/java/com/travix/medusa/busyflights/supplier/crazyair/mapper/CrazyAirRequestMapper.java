package com.travix.medusa.busyflights.supplier.crazyair.mapper;



import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirRequest;
import org.modelmapper.PropertyMap;


public class CrazyAirRequestMapper extends PropertyMap<BusyFlightsRequest, CrazyAirRequest>
{
	@Override
	protected void configure()
	{
		map().setPassengerCount(source.getNumberOfPassengers());
	}
}
