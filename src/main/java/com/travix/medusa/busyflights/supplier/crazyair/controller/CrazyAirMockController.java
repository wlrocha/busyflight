package com.travix.medusa.busyflights.supplier.crazyair.controller;

import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirRequest;
import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;


@RestController
public class CrazyAirMockController
{
	private Random random = new Random(System.currentTimeMillis());
	private static String[] airlines ={"airline 1", "airline 2", "other airline"};

	@PostMapping("/crazyAir")
	public ResponseEntity<List<CrazyAirResponse>> searchCrazyAir(@RequestBody CrazyAirRequest request)
	{
		int size = random.nextInt(15)+5;
		List<CrazyAirResponse> responses = new ArrayList<>(size);
		for(int i=0;i<size;i++)
		{
			responses.add(randomResponse(request));
		}
		return ResponseEntity.ok(responses);
	}

	private CrazyAirResponse randomResponse(CrazyAirRequest request)
	{
		CrazyAirResponse crazyAirResponse = new CrazyAirResponse();
		crazyAirResponse.setAirline(airlines[random.nextInt(airlines.length)]);
		crazyAirResponse.setDepartureAirportCode(request.getOrigin());
		crazyAirResponse.setPrice(random.nextDouble()*3000);
		crazyAirResponse.setCabinclass(random.nextBoolean()?"E":"B");
		crazyAirResponse.setDestinationAirportCode(request.getDestination());
		if(request.getDepartureDate() != null)
		{
			LocalDate departureDate = LocalDate.parse(request.getDepartureDate(), ISO_LOCAL_DATE);
			crazyAirResponse.setDepartureDate(ISO_LOCAL_DATE_TIME.format(departureDate.atTime(LocalTime.now())));
		}
		if(request.getReturnDate() != null)
		{
			LocalDate returnDate = LocalDate.parse(request.getReturnDate(), ISO_LOCAL_DATE);
			crazyAirResponse.setArrivalDate(ISO_LOCAL_DATE_TIME.format(returnDate.atTime(LocalTime.now())));
		}
		return crazyAirResponse;
	}
}
