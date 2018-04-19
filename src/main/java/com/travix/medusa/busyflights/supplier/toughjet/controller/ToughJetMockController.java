package com.travix.medusa.busyflights.supplier.toughjet.controller;

import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetRequest;
import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;


@RestController
public class ToughJetMockController
{
	private Random random = new Random(System.currentTimeMillis());
	private static String[] airlines ={"The airline", "New Airline", "Old Airline"};

	@PostMapping("/toughJet")
	public ResponseEntity<List<ToughJetResponse>> searchToughJet(@RequestBody ToughJetRequest request)
	{
		int size = random.nextInt(18)+3;
		List<ToughJetResponse> responses = new ArrayList<>(size);
		for(int i=0;i<size;i++)
		{
			responses.add(randomResponse(request));
		}
		return ResponseEntity.ok(responses);
	}

	private ToughJetResponse randomResponse(ToughJetRequest request)
	{
		ToughJetResponse toughJetResponse = new ToughJetResponse();
		toughJetResponse.setCarrier(airlines[random.nextInt(airlines.length)]);
		toughJetResponse.setBasePrice(random.nextDouble()*6000);
		toughJetResponse.setTax(random.nextDouble()*30);
		toughJetResponse.setDiscount(random.nextDouble());
		toughJetResponse.setDepartureAirportName(request.getFrom());
		toughJetResponse.setArrivalAirportName(request.getTo());
		if(request.getInboundDate() != null)
		{
			LocalDate departureDate = LocalDate.parse(request.getInboundDate(), ISO_LOCAL_DATE);
			ZonedDateTime zonedDateTime = ZonedDateTime.of(departureDate.atTime(LocalTime.now()), ZoneId.systemDefault());
			toughJetResponse.setInboundDateTime(ISO_INSTANT.format(zonedDateTime));
		}
		if(request.getOutboundDate() != null)
		{
			LocalDate returnDate = LocalDate.parse(request.getOutboundDate(), ISO_LOCAL_DATE);
			ZonedDateTime zonedDateTime = ZonedDateTime.of(returnDate.atTime(LocalTime.now()), ZoneId.systemDefault());
			toughJetResponse.setOutboundDateTime(ISO_INSTANT.format(zonedDateTime));
		}
		return toughJetResponse;
	}

}
