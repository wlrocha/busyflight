package com.travix.medusa.busyflights.supplier.crazyair.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.SearchFlightSupplierService;
import com.travix.medusa.busyflights.supplier.commons.CommonClient;
import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirRequest;
import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirResponse;
import com.travix.medusa.busyflights.supplier.crazyair.properties.CrazyAirProperties;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CrazyAirFlightProviderImpl implements SearchFlightSupplierService
{
	private final CommonClient commonClient;

	private final ModelMapper crazyAirModelMapper;

	private final CrazyAirProperties crazyAirProperties;

	@Override
	public List<BusyFlightsResponse> search(BusyFlightsRequest request) throws Exception
	{
		return commonClient.search(request,
				crazyAirModelMapper,
				crazyAirProperties.getEndpoint(),
				CrazyAirRequest.class,
				CrazyAirResponse[].class);
	}
}
