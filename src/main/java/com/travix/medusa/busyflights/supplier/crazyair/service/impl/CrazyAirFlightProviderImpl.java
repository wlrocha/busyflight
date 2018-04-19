package com.travix.medusa.busyflights.supplier.crazyair.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.SearchFlightSupplierService;
import com.travix.medusa.busyflights.supplier.crazyair.client.CrazyAirClient;
import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirRequest;
import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CrazyAirFlightProviderImpl implements SearchFlightSupplierService
{

	private final CrazyAirClient crazyAirClient;
	private final ModelMapper crazyAirModelMapper;

	@Override
	public List<BusyFlightsResponse> search(BusyFlightsRequest request) throws Exception
	{
		CrazyAirRequest crazyAirRequest = crazyAirModelMapper.map(request, CrazyAirRequest.class);
		List<CrazyAirResponse> crazyAirResponse = crazyAirClient.search(crazyAirRequest);
		List<BusyFlightsResponse> response =crazyAirResponse.stream()
				.map( t-> crazyAirModelMapper.map(t, BusyFlightsResponse.class))
				.collect(Collectors.toList());
		return response;
	}
}
