package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightService;
import com.travix.medusa.busyflights.service.SearchFlightSupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class BusyFlightServiceImpl implements BusyFlightService
{
	private final List<SearchFlightSupplierService> suppliers;

	@Override
	public List<BusyFlightsResponse> searchFlight(BusyFlightsRequest request) throws Exception
	{

		ExecutorService executor = Executors.newFixedThreadPool(suppliers.size());

		List<Callable<List<BusyFlightsResponse>>> searchTasks =
				suppliers.stream()
						.map(supplier -> (Callable<List<BusyFlightsResponse>>)(() -> searchWithSupplier(request, supplier)))
						.collect(Collectors.toList());

		try
		{
			return executor.invokeAll(searchTasks)
					.stream()
					.map(future -> {
						try {
							return future.get();
						}
						catch (Exception e) {
							throw new IllegalStateException(e);
						}
					})
					.flatMap(List::stream)
					.sorted(Comparator.comparing(BusyFlightsResponse::getFare))
					.collect(Collectors.toList());
		}
		catch (InterruptedException e)
		{
			log.error(e.getMessage(), e);
			throw new Exception("Error doing the search");
		}
	}

	private List<BusyFlightsResponse> searchWithSupplier(BusyFlightsRequest request, SearchFlightSupplierService p)
	{
		try
		{
			return p.search(request);
		} catch (Exception e){
			log.error(e.getMessage(), e);
			return Collections.EMPTY_LIST;
		}
	}
}
