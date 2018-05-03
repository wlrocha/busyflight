package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class BusyFlightController
{
	private final BusyFlightService busyFlightService;

	@GetMapping("/flight/search")
	public ResponseEntity<List<BusyFlightsResponse>> searchFlight(@Valid BusyFlightsRequest request) throws Exception
	{
		List<BusyFlightsResponse> response = busyFlightService.searchFlight(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/test")
	public ResponseEntity<List<BusyFlightsResponse>> test() throws Exception{
		BusyFlightsRequest dummyRequest = new BusyFlightsRequest();
		dummyRequest.setOrigin("LHR");
		dummyRequest.setDestination("AMS");
		dummyRequest.setDepartureDate("2011-12-03");
		dummyRequest.setReturnDate("2018-11-01");
		dummyRequest.setNumberOfPassengers(2);
		return searchFlight(dummyRequest);
	}
}
