package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightService;
import com.travix.medusa.busyflights.service.SearchFlightSupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class BusyFlightServiceImpl implements BusyFlightService
{
	private final List<SearchFlightSupplierService> suppliers;

	@Override
	public List<BusyFlightsResponse> searchFlight(BusyFlightsRequest request)
	{
		return suppliers.stream()
							.parallel() //all providers can be requested in parallel
							.map( s -> searchWithSupplier(request, s)) //do a search request to supplier s (same request for all)
							.filter(Optional::isPresent)//if result is not present some error occurred
							.map(Optional::get)//unwrap the list from the optional
							.flatMap(List::stream)// merge all results in one stream (each result is a list)
							.sorted(Comparator.comparing(BusyFlightsResponse::getFare))//sort by fare asc
							.collect(Collectors.toList());
	}

	private Optional<List<BusyFlightsResponse>> searchWithSupplier(BusyFlightsRequest request, SearchFlightSupplierService p)
	{
		try
		{
			return Optional.of(p.search(request));
		} catch (Exception e){
			log.error(e.getMessage(), e);
			return Optional.empty();
		}
	}
}
