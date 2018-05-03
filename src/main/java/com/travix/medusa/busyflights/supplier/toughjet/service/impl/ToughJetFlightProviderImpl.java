package com.travix.medusa.busyflights.supplier.toughjet.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.SearchFlightSupplierService;
import com.travix.medusa.busyflights.supplier.commons.CommonClient;
import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetRequest;
import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetResponse;
import com.travix.medusa.busyflights.supplier.toughjet.properties.ToughJetProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ToughJetFlightProviderImpl implements SearchFlightSupplierService
{
	private final CommonClient commonClient;
	private final ModelMapper toughJetModelMapper;
	private final ToughJetProperties toughJetProperties;


	@Override
	public List<BusyFlightsResponse> search(BusyFlightsRequest request) throws Exception
	{
		return commonClient.search(request,
											toughJetModelMapper,
											toughJetProperties.getEndpoint(),
											ToughJetRequest.class,
											ToughJetResponse[].class);
	}
}
