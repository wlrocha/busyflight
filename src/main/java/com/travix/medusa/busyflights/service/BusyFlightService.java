package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;


public interface BusyFlightService
{
	List<BusyFlightsResponse> searchFlight(BusyFlightsRequest request);
}
