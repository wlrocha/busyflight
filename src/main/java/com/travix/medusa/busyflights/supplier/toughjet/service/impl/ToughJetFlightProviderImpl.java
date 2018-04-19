package com.travix.medusa.busyflights.supplier.toughjet.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.SearchFlightSupplierService;
import com.travix.medusa.busyflights.supplier.toughjet.client.ToughJetClient;
import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetRequest;
import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ToughJetFlightProviderImpl implements SearchFlightSupplierService
{
	private final ToughJetClient toughJetClient;
	private final ModelMapper toughJetModelMapper;

	@Override
	public List<BusyFlightsResponse> search(BusyFlightsRequest request) throws Exception
	{
		ToughJetRequest toughJetRequest = toughJetModelMapper.map(request, ToughJetRequest.class);
		List<ToughJetResponse> toughJetResponse = toughJetClient.search(toughJetRequest);
		List<BusyFlightsResponse> response =toughJetResponse.stream()
				.map( t-> toughJetModelMapper.map(t, BusyFlightsResponse.class))
				.collect(Collectors.toList());
		return response;
	}
}
